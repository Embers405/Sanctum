package io.ruin.model.activities.raids.tob.dungeon.boss.bloat;

import io.ruin.api.utils.Random;
import io.ruin.model.entity.Entity;
import io.ruin.model.entity.npc.NPC;
import io.ruin.model.entity.npc.NPCCombat;
import io.ruin.model.entity.player.Boundary;
import io.ruin.model.entity.player.Player;
import io.ruin.model.entity.shared.listeners.DeathListener;
import io.ruin.model.entity.shared.listeners.HitListener;
import io.ruin.model.event.CycleEvent;
import io.ruin.model.event.CycleEventContainer;
import io.ruin.model.event.CycleEventHandler;
import io.ruin.model.map.Position;
import io.ruin.model.map.Projectile;
import io.ruin.model.map.Region;
import io.ruin.utility.Misc;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.stream.Collectors;

import static io.ruin.model.map.route.routes.DumbRoute.stepBloat;



public class BloatCombatNew extends NPCCombat {


    private static final Position[] corners = {
            new Position(3288, 4440), //bottom left
            new Position(3288, 4451), //top left
            new Position(3299, 4451), //top right
            new Position(3299, 4440) //bottom right
    };

    private static final int SLEEPING = 8082; //sleep animation
    private int corner = 0; //corner flag
    @Getter
    @Setter
    private int stepsTillStop; //steps till bloat stops walking and then sleeps
    private boolean DR = true; //damage reduction
    @Getter @Setter private boolean stopped; //if bloat has stopped walking
    @Getter @Setter private boolean sleeping;
    private List<Entity> targets;

    private static final int MIN_FLESH = 1570, MAX_FLESH = 1573;
    private static final Boundary ARENA = new Boundary(3288, 4440, 3303, 4455);
   // private static final Projectile FLYS = new Projectile(1569, 45, 28, 0, 100, 0, 50);
   private final Projectile flys = new Projectile(1569, 45, 28, 0, 100, 0, 50, 10);
    private boolean flesh;

    @Override
    public void init() {
        npc.setMaxHp((int) ((double) npc.getMaxHp() * 0.75));
        stepsTillStop = Misc.random(40, 80);
        process();
        npc.hitListener = new HitListener().preDefend(hit -> {
            //if (sleepytime) {
            //    hit.boostDefence(0.75);
          //  }
        });
        npc.deathStartListener = (DeathListener.Simple) () -> {
            npc.animate(8085);
        };


  //      setCanAttack(false); //this will stop following when he finds a target.
   //     npc.setFacePlayer(false); //so he doesnt try to look at the attacking players
  //      npc.getDefinition().setSize(5); //TODO adjust pathing locations for size 5
     //   npc.getHealth().isNotSusceptibleTo(HealthStatus.POISON);
      //  npc.getHealth().isNotSusceptibleTo(HealthStatus.VENOM);
     //   npc.setNeverWalkHome(true);
     //   npc.setNoRespawn(true);
       // npc.getCombat().setAllowRespawn(false);
    }

    /* (non-Javadoc)
     * @see ethos.model.npcs.combat.CombatScript#attack(ethos.model.npcs.NPC, ethos.model.entity.Entity)
     */
    @Override
    public boolean attack() {
        return false;
    }

   // @Override
   // public void process(NPC npc, Entity target) {
   public void process() {
      //  super.process(target.npc, target.player);

       //process();
        System.out.println("[BLOAT WALKING]");
        if(npc.dead())
            return;
      //  npc.freezeTimer = 0;
        //targets = (List<Entity>) findAggressionTarget();
        targets = (List<Entity>) target.player;
        if (corner >= corners.length) {
            corner = 0;
        }
        if (!npc.getPosition().equalsIgnoreHeight(corners[corner]) && !isStopped()) { //hasnt reached the corner yet
         //   NPCDumbPathFinder.walkTowards(npc, corners[corner].getX(), corners[corner].getY());
            stepBloat(npc, corners[corner].getX(), corners[corner].getY());
            System.out.println("[BLOAT WALKING]");
            if (stepsTillStop-- <= 0) {
                setStopped(true);
            }
            if (npc.getPosition().equalsIgnoreHeight(corners[corner]) && !isSleeping()) {
                corner++;
            }
        } else {
            if (isStopped() && getStepsTillStop() <= 0 && !isSleeping()) {
                DR = false;
                shutdown(npc);
            }
        }
        if (!isSleeping()) {
            checkForLineOfSight(npc);
        }
        boolean present = targets.stream().anyMatch(t -> Boundary.isIn(t, Boundary.BLOAT_ARENA));
        if (present && !flesh && !isSleeping()) {
            fleshFall(npc, targets.get(0));
        }
    }

  //  @Override
   // public void handleDeath(NPC npc, Entity source) {
   //     List<Entity> players = getPossibleTargets(npc, true);
   //     players.forEach(player -> {

            //player.asPlayer().killedMaiden = true;
   //         player.asPlayer().sendMessage("@red@You have defeated the Pestilient Bloat!");
   //         Theatre instance = player.asPlayer().getTheatreInstance();
   //         if (instance != null) {
   //             player.asPlayer().sendMessage("Time elapsed this raid: @red@"+instance.getTimeElapsed()+"@bla@.");
   //             Server.getGlobalObjects().add(new GlobalObject(4388, 3269, 4447, npc.getHeight(), 0, 10, 180, -1));
   //         }
  //          player.asPlayer().sendMessage("You now have @red@"+player.asPlayer().theatrePoints+"@bla@ points!");
  //      });
  //  }

  //  @Override
  //  public double getDamageReduction(NPC npc) {
  //      return DR ? 0 : 1;
  //  }

    /* (non-Javadoc)
     * @see ethos.model.npcs.combat.CombatScript#getAttackDistance(ethos.model.npcs.NPC)
     */
 //   @Override
   // public int getAttackDistance(NPC npc) {
   //     return 20;
   // }

    /* (non-Javadoc)
     * @see ethos.model.npcs.combat.CombatScript#ignoreProjectileClipping()
     */
  //  @Override
  //  public boolean ignoreProjectileClipping() {
  //      return true;
  //  }

    /**
     * Handles line of sight attacks
     * @param npc
     */
    private void checkForLineOfSight(NPC npc) {
        for (Entity target : targets) {
            if (!Boundary.isIn(target, Boundary.BLOAT_ARENA))
                return;
           // if (!PathChecker.isProjectilePathClear(npc.getX(), npc.getY(), npc.getHeight(), target.getX(), target.getY())
             //       && !PathChecker.isProjectilePathClear(target.getX(), target.getY(), target.getHeight(), npc.getX(), npc.getY())) {
            if (!Region.isProjectilePathClear(npc.getX(), npc.getY(), npc.getHeight(), target.getX(), target.getY())
                    && !Region.isProjectilePathClear(target.getX(), target.getY(), target.getHeight(), npc.getX(), npc.getY())) {
                continue;
            }
            if (target.isPlayer()) {
              //  handleHit(npc, target, CombatType.SPECIAL, FLYS, new Graphic(1569), new Hit(Hitmark.HIT, Misc.random(1, 23), 3));
            }
        }
    }

    /**
     * Handles falling flesh
     * @param npc
     */
    private void fleshFall(NPC npc, Entity target) {
        flesh = true;
        CycleEvent event = new CycleEvent() {

            int cycle = 0;
            List<Position> locs = ARENA.getRandomLocations(Misc.random(10, 20), npc.getHeight());

            @Override
            public void execute(CycleEventContainer container) {
                if(npc.dead()) {
                    container.stop();
                    return;
                }
                if (cycle == 0) {
                    for (Position loc : locs) {
                        if (target.isPlayer()) {
                         //   target.asPlayer().getPA().createPlayersStillGfx(Misc.random(MIN_FLESH, MAX_FLESH), loc.getX(), loc.getY(), 0, 0);
                        }
                    }
                } else if (cycle == 4) {
                    for (Entity target : targets) {
                        for (Position loc : locs) {
                            if (target.getPosition().equalsIgnoreHeight(loc)) {
                             //   target.appendDamage(Misc.random(20, 30), Hitmark.HIT);
                            }
                        }
                    }
                } else if (cycle >= 8) {
                    flesh = false;
                    container.stop();
                }
                cycle++;
            }

        };
        CycleEventHandler.getSingleton().addEvent(-1, npc, event, 1);
    }

    /**
     * Handles the sleeping bit
     * @param npc
     */
    private void shutdown(NPC npc) {
        npc.animate(SLEEPING);
        setSleeping(true);
        CycleEvent event = new CycleEvent() {

            int duration = 0;

            @Override
            public void execute(CycleEventContainer container) {
                if (duration >= 29) {
                    setStopped(false);
                    setStepsTillStop(Misc.random(40, 80));
                    setSleeping(false);
                    DR = true;
                    container.stop();
                }
                duration++;
            }

        };
        CycleEventHandler.getSingleton().addEvent(-1, npc, event, 1);
    }

    @Override
    public void follow() {
        //LEAVE MPTY FOR NPC TO NOT MOVE
    }

    protected Entity findAggressionTarget() {
        if (npc.localPlayers().isEmpty())
            return null;
        if (npc.hasTarget())
            return null;
        List<Player> targets = npc.localPlayers().stream()
                .filter(this::canAggro)
                .collect(Collectors.toList());
        if (targets.isEmpty())
            return null;
        return Random.get(targets);
    }

}
