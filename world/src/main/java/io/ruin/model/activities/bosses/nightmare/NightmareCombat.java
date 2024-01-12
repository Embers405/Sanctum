package io.ruin.model.activities.bosses.nightmare;

import io.ruin.api.utils.Random;
import io.ruin.cache.Color;
import io.ruin.model.combat.Hit;
import io.ruin.model.entity.Entity;
import io.ruin.model.entity.npc.NPCCombat;
import io.ruin.model.entity.player.Player;
import io.ruin.model.entity.shared.listeners.HitListener;
import io.ruin.model.item.Item;
import io.ruin.model.item.loot.LootItem;
import io.ruin.model.item.loot.LootTable;
import io.ruin.model.map.Position;
import io.ruin.model.map.Projectile;
import io.ruin.model.map.ground.GroundItem;
import io.ruin.model.map.route.routes.ProjectileRoute;
import io.ruin.model.skills.prayer.Prayer;
import io.ruin.utility.Broadcast;
import io.ruin.utility.Misc;

public class NightmareCombat extends NPCCombat {

	private static final int MAGIC_ANIMATION = 8595, RANGE_ANIMATION = 8596, MAGIC_PROJECTILE = 1764, RANGED_PROJECTILE = 1766;
	
	private SpecialAttacks special;

	private Nightmare nightmare;

	public NightmareCombat(Nightmare nightmare) {
		this.nightmare = nightmare;
	}
	
	@Override
	public void init() {
		if (nightmare == null) return;
		nightmare.hitListener = new HitListener()
                .postDefend(this::postDefend)
                .postDamage(this::afterDamaged);
		nightmare.deathStartListener = (entity, killer, killHit) -> {
			nightmare.animate(8612);
			nightmare.remove();
			for (Player player : nightmare.localPlayers()) {
				for (int i = 0; i < 2; i++) {
					Item rolled = rollRegular();
					int amount = rolled.getAmount();
					if (amount > 1 && !rolled.getDef().stackable && !rolled.getDef().isNote())
						rolled.setId(rolled.getDef().notedId);
					new GroundItem(rolled).owner(player).position(player.getPosition().getX(),player.getPosition().getY(),player.getPosition().getZ()).spawn();
					player.sendMessage("<shad=000000>"+Color.COOL_BLUE.wrap("You got " + rolled.getDef().name +" X "+ rolled.getAmount())+"</shad>");
					if (rolled.getId() == 24420 || rolled.getId() == 24421 || rolled.getId() == 24419 || rolled.getId() == 24417 || rolled.getId() == 24514 || rolled.getId() == 24517 || rolled.getId() == 24511 || rolled.getId() == 24422) {
						Broadcast.WORLD.sendNews("<shad=000000>"+Color.RAID_PURPLE.wrap("[RARE DROP] ")+player.getName()+" has just received "+Color.DARK_RED.wrap(rolled.getDef().name)+" from Nightmare!"+"</shad>");
					}
				}
				new GroundItem(592,1).owner(player).position(player.getPosition().getX(),player.getPosition().getY(),player.getPosition().getZ()).spawn();
				new GroundItem(995, Random.get(100000, 150000)).owner(player).position(player.getPosition().getX(),player.getPosition().getY(),player.getPosition().getZ()).spawn();
			}

			killer.player.sendMessage("You have defeated The Nightmare!");
			killer.player.sendMessage("You can leave by using the energy barrier.");
        };

		nightmare.hitsUpdate.hpBarType = 8;
//		nightmare.addEvent(event -> {
//            while (true) {
//                if (!npc.getCombat().isDead() && !npc.isHidden() && !npc.isRemoved()
//                        && (npc.localPlayers().isEmpty() || npc.localPlayers().stream().noneMatch(p -> ProjectileRoute.allow(npc, p)))) {// no players in sight, reset
//                    restore();
//                }
//                event.delay(3);
//            }
//        });
    }

	public static LootTable regularTable = new LootTable()
			.addTable(20,
					new LootItem(1320, Random.get(2, 5), 3), //Rune 2h sword
					new LootItem(1276, Random.get(2, 5), 3), //Rune pickaxe
					new LootItem(1304, Random.get(2, 5), 2), //Rune longsword
					new LootItem(1290, Random.get(2, 5), 2), //Rune sword
					new LootItem(5316, Random.get(3, 5), 3), //magic seed

					new LootItem(2, 3000, 3), //cannonballs x3000
					new LootItem(6686, 50, 3), //saradomin brew noted x50
					new LootItem(12696, 25, 1), //super combat pooition noted x25
					new LootItem(6199, 1, 1), //mystery box
					new LootItem(5730, 1, 3) //dragon spear
			)
			.addTable(20,
					new LootItem(1128, 5, 3),
					new LootItem(4088, Random.get(2, 3), 3),
					new LootItem(22804, Random.get(50, 125), 3),
					new LootItem(23648, Random.get(50, 125), 3)
			)
			.addTable(20,
					new LootItem(561, Random.get(600, 700), 5),
					new LootItem(21880, Random.get(300, 500), 5),
					new LootItem(563, Random.get(500, 600), 5),
					new LootItem(565, Random.get(500, 700), 5),
					new LootItem(560, Random.get(600, 700), 5)
			)
			.addTable(20,
					new LootItem(450, Random.get(25, 50), 4),
					new LootItem(452, Random.get(15, 20), 4),
					new LootItem(454, Random.get(115, 120), 3)
			)
			.addTable(20,
					new LootItem(995, Random.get(200000, 250000), 3),
					new LootItem(5300, 1, 3),
					new LootItem(1514, Random.get(15, 20), 3),
					new LootItem(3024, 3, 1),
					new LootItem(3052, 3, 1),
					new LootItem(10506,5,50,5),

					new LootItem(12073, 1, 1)
			)
			.addTable(1,
					new LootItem(6694, Random.get(30, 90), 20), //Crushed nest
					new LootItem(1513, Random.get(70, 150), 20), //Magic logs
					new LootItem(258, Random.get(10, 20), 20), //Rannar
					new LootItem(1206, Random.get(10, 20), 20), //Bronze dagger
					new LootItem(24420, 1, 1),
					new LootItem(24421, 1, 1),
					new LootItem(24419, 1, 1),
					new LootItem(24417, 1, 1)
			)
			.addTable(1,
					new LootItem(6694, Random.get(30, 90), 20), //Crushed nest
					new LootItem(1513, Random.get(70, 150), 20), //Magic logs
					new LootItem(258, Random.get(10, 20), 20), //Rannar
					new LootItem(1206, Random.get(10, 20), 20), //Bronze dagger
					new LootItem(24514, 1, 1),
					new LootItem(24517, 1, 1),
					new LootItem(24511, 1, 1),
					new LootItem(24422, 1, 2)
			);

	private static Item rollRegular() {
		return regularTable.rollItem();
	}

	@Override
	protected boolean canAggro(Player player) {
		return nightmare != null && nightmare.isAttackable();
	}

	private void postDefend(Hit hit) {
    	
    }

    private void afterDamaged(Hit hit) {
        if(isDead()) return;
    }

	@Override
	public void follow() {
        follow(1);
      }

	@Override
	public void setTarget(Entity target) {
		if (!nightmare.isAttackable()) {
			return;
		}
		super.setTarget(target);
	}

	@Override
	public boolean attack() {
		if (nightmare == null) {
			return true;
		}
		/*
		 * Special attack.
		 */
		if (special != null) {
			nightmare.animate(special.animation);
			special.run(nightmare);
			special = null;
			return true;
		}
		
		/*
		 * Melee attack.
		 */
//		for (Player p : )

		/*
		 * Default attack.
		 */
		if (Misc.random2(10) > 5) {
			for (Entity victim : nightmare.getPossibleTargets(64, true, false)) {
				nightmare.animate(MAGIC_ANIMATION);
				nightmare.getCombat().setTarget(victim);
				nightmare.addEvent(e->{
					e.delay(1);
					Projectile pr = new Projectile(MAGIC_PROJECTILE, 110, 90, 30, 56, 10, 10, 64);
					pr.send(nightmare, victim);
					});
				final Position dest = victim.getPosition();
				int delay = 60;
				if (nightmare.getCentrePosition().distance(dest) == 1) {
					delay = 60;
				} else if (nightmare.getCentrePosition().distance(dest) <= 5) {
					delay = 80;
				} else if (nightmare.getCentrePosition().distance(dest) <= 8) {
					delay = 100;
				} else {
					delay = 120;
				}
				throttleFarcast(nightmare, (Player) victim, delay, dest, Prayer.PROTECT_FROM_MAGIC);
			}
		} else {
			for (Entity victim : nightmare.getPossibleTargets(64, true, false)) {
				int delay = 60;
				nightmare.animate(RANGE_ANIMATION);
				nightmare.getCombat().setTarget(victim);
				nightmare.addEvent(e->{
					e.delay(1);
					Projectile pr = new Projectile(RANGED_PROJECTILE, 110, 90, 30, 56, 10, 10, 64);
					pr.send(nightmare, victim);
					});
				final Position dest = victim.getPosition();
				if (nightmare.getCentrePosition().distance(dest) == 1) {
					delay = 60;
				} else if (nightmare.getCentrePosition().distance(dest) <= 5) {
					delay = 80;
				} else if (nightmare.getCentrePosition().distance(dest) <= 8) {
					delay = 100;
				} else {
					delay = 120;
				}
				throttleFarcast(nightmare, (Player) victim, delay, dest, Prayer.PROTECT_FROM_MISSILES);
			}
		}
		return true;
	}


	private void throttleFarcast(Entity nightmare, Player victim, int delay, Position dest, Prayer prayer) {
		nightmare.addEvent(e->{
			e.delay((delay / 20) + 1);
			int max = !victim.getPrayer().isActive(prayer) ? 40 : 0;
			victim.hit(new Hit().randDamage(max));
		});
	}
	public void setSpecial(SpecialAttacks attack) {
		special = attack;
	}

}
