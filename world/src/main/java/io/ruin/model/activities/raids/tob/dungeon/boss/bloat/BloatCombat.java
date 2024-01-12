package io.ruin.model.activities.raids.tob.dungeon.boss.bloat;

import com.google.common.collect.Lists;
import io.ruin.api.utils.Random;
import io.ruin.model.World;
import io.ruin.model.combat.Hit;
import io.ruin.model.entity.npc.NPCCombat;
import io.ruin.model.entity.shared.listeners.DeathListener;
import io.ruin.model.entity.shared.listeners.HitListener;
import io.ruin.model.event.CycleEvent;
import io.ruin.model.event.CycleEventContainer;
import io.ruin.model.event.CycleEventHandler;
import io.ruin.model.map.Bounds;
import io.ruin.model.map.Direction;
import io.ruin.model.map.Position;

import java.util.Collections;
import java.util.List;

import static io.ruin.model.map.route.routes.DumbRoute.stepBloat;

public class BloatCombat extends NPCCombat {


    private static final int FLIES_GFX = 1568;

    @Override
    public void init() {
        npc.setMaxHp((int) ((double) npc.getMaxHp() * 0.75));
     //   flesh(0, 2500);
     //   sleepyTime();
        moveBloat();
        npc.hitListener = new HitListener().preDefend(hit -> {
            if (sleepytime) {
                hit.boostDefence(0.75);
            }
        });
        npc.deathStartListener = (DeathListener.Simple) () -> {
            npc.animate(8085);
        };
    }



    @Override
    public void follow() {

    }

    @Override
    public boolean attack() {
        return false;
    }

    public boolean sleepytime = false;

    public void sleepyTime() {
        npc.startEvent(event -> {
            System.out.println("[BLOAT WALKING]"+count);
            event.delay(5);
            handleMovement();
            tickNextState();
            sleepyTime();
            if (actionState == ActionState.WALKING) {
                count--;
                if (count == 0) {
                    sleepytime = true;
                    System.out.println("[BLOAT] Sleepy time");
                    npc.animate(8082);
                    while (npc.isAnimating()) {
                        npc.lock();
                        event.delay(13);
                        npc.unlock();
                    }
                }
            } else if (actionState == ActionState.SLEEPING) {
                System.out.println("[BLOAT SLEEPING]" + count);
                event.delay(1);
                count = 0;
                if (stateTimer == actionState.getStateTimer() - 1) {
                    count = 30;
                    sleepytime = false;
                    flesh();
                    stompPlayers();
                    handleMovement();
                    tickNextState();
                    System.out.println("[BLOAT WAKEY WAKEY]" + count);
                }
            }
        });
    }

    public Position getAbsolute(int localX, int localY) {
        return new Position(npc.getPosition().getRegion().baseX + localX, npc.getPosition().getRegion().baseY + localY, npc.getPosition().getZ());
    }

    public static int count = 30;

   // public void flesh(int delay, int duration) {
        public void flesh() {
        npc.addEvent(event -> {
            event.setCancelCondition(this::isDead);
           // event.delay(delay);
            event.delay(5);
            int ticks = 0;
          //  while (ticks < duration && !sleepytime) {
            while (ticks < 5 && !sleepytime) {
                for (int i = 0; i < 8; i++) {
                    Position position;
                    position = getAbsolute(Random.get(23, 40), Random.get(23, 40));

                    if (position.getTile().isTileFreeCheckDecor() && position.inBounds(npc.getPosition().getRegion().bounds)) {

                        if (Random.get() <= 0.5)
                            World.sendGraphics(1572, 0, 5, position);
                        else
                            World.sendGraphics(1573, 0, 5, position);

                        npc.localPlayers().forEach(p -> {
                            if (p.getPosition().equals(position)) {
                                p.hit(new Hit(npc).randDamage(30, 60));
                            }
                        });
                        // Flies
                        damagePlayersInLos();
                    }
                }
                ticks += 2;
                event.delay(2);
            }
        });
    }

    private void damagePlayersInLos() {
        Bounds psouth = new Bounds(npc.getPosition().getRegion().baseX + 23, npc.getPosition().getRegion().baseY + 23, npc.getPosition().getRegion().baseX + 39, npc.getPosition().getRegion().baseY + 28, 0);
        Bounds pnorth = new Bounds(npc.getPosition().getRegion().baseX + 35, npc.getPosition().getRegion().baseY + 23, npc.getPosition().getRegion().baseX + 35, npc.getPosition().getRegion().baseY + 40, 0);
        // ^^ DONE South East Corner -> Span to North East and South West Corner

        Bounds psouth1 = new Bounds(npc.getPosition().getRegion().baseX + 24, npc.getPosition().getRegion().baseY + 24, npc.getPosition().getRegion().baseX + 28, npc.getPosition().getRegion().baseY + 39, 0);
        Bounds pnorth1 = new Bounds(npc.getPosition().getRegion().baseX + 24, npc.getPosition().getRegion().baseY + 35, npc.getPosition().getRegion().baseX + 39, npc.getPosition().getRegion().baseY + 39, 0);
        // ^^ DONE North West Corner -> Span to North East Corner and South West corner

        Bounds psouth2 = new Bounds(npc.getPosition().getRegion().baseX + 24, npc.getPosition().getRegion().baseY + 24, npc.getPosition().getRegion().baseX + 28, npc.getPosition().getRegion().baseY + 39, 0);
        Bounds pnorth2 = new Bounds(npc.getPosition().getRegion().baseX + 24, npc.getPosition().getRegion().baseY + 24, npc.getPosition().getRegion().baseX + 39, npc.getPosition().getRegion().baseY + 28, 0);
        // ^^ DONE South West Corner -> Span to North West Corner and South East Corner

        Bounds psouth3 = new Bounds(npc.getPosition().getRegion().baseX + 35, npc.getPosition().getRegion().baseY + 24, npc.getPosition().getRegion().baseX + 39, npc.getPosition().getRegion().baseY + 39, 0);
        Bounds pnorth3 = new Bounds(npc.getPosition().getRegion().baseX + 24, npc.getPosition().getRegion().baseY + 35, npc.getPosition().getRegion().baseX + 39, npc.getPosition().getRegion().baseY + 39, 0);
        // ^^ DONE North East Corner -> Span to South East Corner and North West Corner
        npc.localPlayers().forEach(plr -> {
            if (npc.getPosition().inBounds(psouth) && plr.getPosition().inBounds(psouth) || npc.getPosition().inBounds(pnorth) && plr.getPosition().inBounds(pnorth)) {
                plr.graphics(FLIES_GFX);
                plr.localPlayers().forEach(player -> {
                    if (player.getPosition().isWithinDistance(plr.getPosition(), 3)) {
                        player.graphics(FLIES_GFX);
                    }
                });
            } else if (npc.getPosition().inBounds(psouth1) && plr.getPosition().inBounds(psouth1) || npc.getPosition().inBounds(pnorth1) && plr.getPosition().inBounds(pnorth1)) {
                plr.graphics(FLIES_GFX);
                plr.localPlayers().forEach(player -> {
                    if (player.getPosition().isWithinDistance(plr.getPosition(), 3)) {
                        player.graphics(FLIES_GFX);
                    }
                });
            } else if (npc.getPosition().inBounds(psouth2) && plr.getPosition().inBounds(psouth2) || npc.getPosition().inBounds(pnorth2) && plr.getPosition().inBounds(pnorth2)) {
                plr.graphics(FLIES_GFX);
                plr.localPlayers().forEach(player -> {
                    if (player.getPosition().isWithinDistance(plr.getPosition(), 3)) {
                        player.graphics(FLIES_GFX);
                    }
                });
            } else if (npc.getPosition().inBounds(psouth3) && plr.getPosition().inBounds(psouth3) || npc.getPosition().inBounds(pnorth3) && plr.getPosition().inBounds(pnorth3)) {
                plr.graphics(FLIES_GFX);
                plr.localPlayers().forEach(player -> {
                    if (player.getPosition().isWithinDistance(plr.getPosition(), 3)) {
                        player.graphics(FLIES_GFX);
                    }
                });
            }
        });

    }



    private enum ActionState {
        WALKING,
        SLEEPING
        ;

        ActionState() {
        }

        public int getStateTimer() {
            if (this == WALKING) {
                return 30;
            } else if (this == SLEEPING) {
              //  return AnimationLength.getFrameLength(8082);
                return 4;
            }

            throw new IllegalStateException("No length for " + this);
        }
    }


    public void process() {
        npc.startEvent(event -> {
            event.setCancelCondition(this::isDead);
            process();
            int cycle = 0;
           // if (actionState == ActionState.WALKING) {
            if (cycle == 1) {
                System.out.println("[BLOAT WALKING]");
                handleMovement();
                npc.getMovement().process();
                damagePlayersInLos();
                tickNextState();
                npc.animate(65_535);
         //   } else if (actionState == ActionState.SLEEPING) {
            } else if (cycle == 4) {
                System.out.println("[BLOAT SLEEPING]");
                    tickNextState();
                    npc.animate(8082);
                    while (npc.isAnimating()) {
                        npc.lock();
                       event.delay(13);
                        npc.unlock();
                    }
          // if (stateTimer == actionState.getStateTimer() - 1) {
            if (cycle >= 8) {
                 System.out.println("[BLOAT STOMP]");
                    stompPlayers();
                    // fleshFall();
                   // flesh(0, 2500);
                   flesh();
                    event.delay(30);
                }
                cycle++;
            }
        });
    }

    private void tickNextState() {
            if (stateTimer++ >= actionState.getStateTimer()) {
                if (actionState == ActionState.WALKING) {
                    actionState = ActionState.SLEEPING;
                    sleepytime = true;
                    npc.animate(8082, 13);
                    while (npc.isAnimating()) {
                        npc.lock();
                    }
                } else if (actionState == ActionState.SLEEPING) {
                    actionState = ActionState.WALKING;
                    npc.animate(65_535);
                    sleepytime = false;
                    npc.unlock();
                }

                stateTimer = 0;
            }
    }


    private static final Position MOVE_WEST_POSITION = new Position(3299, 4440, 0);
    private static final Position MOVE_SOUTH_POSITION = new Position(3299, 4451, 0);
    private static final Position MOVE_EAST_POSITION = new Position(3288, 4451, 0);
    private static final Position MOVE_NORTH_POSITION = new Position(3288, 4440, 0);

    private enum MovementState {
        SOUTH(MOVE_WEST_POSITION, Direction.SOUTH, MovementLos.SOUTH),
        WEST(MOVE_NORTH_POSITION, Direction.WEST, MovementLos.WEST),
        NORTH(MOVE_EAST_POSITION, Direction.NORTH, MovementLos.NORTH),
        EAST(MOVE_SOUTH_POSITION, Direction.EAST, MovementLos.EAST),
        ;

        private final Position end;
        private final Direction direction;
        private final MovementLos movementLos;

        MovementState(Position end, Direction direction, MovementLos movementLos) {
            this.end = end;
            this.direction = direction;
            this.movementLos = movementLos;
        }
    }

    private enum MovementLos {
        SOUTH(new Position(3299, 4440, 0), new Position(3303, 4455, 0)),
        WEST(new Position(3288, 4440, 0), new Position(3303, 4444, 0)),
        NORTH(new Position(3288, 4440, 0), new Position(3292, 4455, 0)),
        EAST(new Position(3288, 4451, 0), new Position(3303, 4455, 0)),
        ;

        private final List<Position> positions;

        MovementLos(Position start, Position end) {
            List<Position> positionList = Lists.newArrayList();
            for (int x = start.getX(); x <= end.getX(); x++) {
                for (int y = start.getY(); y <= end.getY(); y++) {
                    positionList.add(new Position(x, y));
                }
            }
            positions = Collections.unmodifiableList(positionList);
        }
    }

    private MovementState movementState = MovementState.SOUTH;
    private void handleMovement() {
        npc.localPlayers().forEach(plr -> {
      //  if (npc.getPosition().equals(movementState.end.withHeight(plr.getHeight()))) {
        if (npc.getPosition().equals(movementState.end.withHeight(0))) {
            setNextMovement();
        } else {
            Position nextPositionDelta = movementState.direction.getDeltaPosition();
            Position nextPositionAbsolute = new Position(npc.getAbsX() + nextPositionDelta.getX(), npc.getAbsY() + nextPositionDelta.getY());
            stepBloat(npc, nextPositionAbsolute.getX(), nextPositionAbsolute.getY());
         //   npc.step(nextPositionAbsolute.getX(), nextPositionAbsolute.getY(), StepType.FORCE_WALK);
        }
    });
    }


    private void stompPlayers() {
       // getInstance().getPlayers().forEach(plr -> {
        npc.localPlayers().forEach(plr -> {
           // for (Position position : getTarget()) {
                if (plr.getPosition().isWithinDistance(plr.getPosition(), 2)) {
                    if (plr.dead())
                        return;
                    plr.hit(new Hit(npc).randDamage(30, 20));
                    return;
                }
          //  }
        });
    }



    private void setNextMovement() {
        movementState = MovementState.values()[(movementState.ordinal() + 1) % MovementState.values().length];
    }


    private ActionState actionState = ActionState.WALKING;
    private int stateTimer = 0;


    public Direction walkDirection = Direction.NONE;
    public Direction runDirection = Direction.NONE;



    private void moveBloat() {

      //  NPC npc = NPCCombat.getNpc(8359);
        CycleEvent event = new CycleEvent() {
            void moveBloat() {
                moveBloat();
            }

            int cycle = 0;
            @Override
            public void execute(CycleEventContainer container) {
            //    NPC npc = NPCCombat.getNpc(8359);
            //    if(npc.dead()) {
            //        container.stop();
             //       return;
             //   }
                if (cycle == 0) {
                    System.out.println("[BLOAT WALKING]");
                    handleMovement();
                    npc.getMovement().process();
                    damagePlayersInLos();
                    tickNextState();
                    npc.animate(65_535);
                } else if (cycle == 4) {
                    System.out.println("[BLOAT SLEEPING]");
                    tickNextState();
                    npc.animate(8082);
                    while (npc.isAnimating()) {
                        npc.lock();
                        npc.unlock();
                    }
                } else if (cycle >= 8) {
                    System.out.println("[BLOAT STOMP]");
                    stompPlayers();
                    flesh();
                    container.stop();
                }
                cycle++;
            }

        };
        CycleEventHandler.getSingleton().addEvent(-1, npc, event, 1);
    }


}
