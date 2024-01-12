package io.ruin.model.activities.bosses.nex;

import io.ruin.model.World;
import io.ruin.model.combat.CombatUtils;
import io.ruin.model.combat.Hit;
import io.ruin.model.entity.Entity;
import io.ruin.model.entity.npc.NPC;
import io.ruin.model.entity.npc.NPCAction;
import io.ruin.model.entity.player.Player;
import io.ruin.model.entity.shared.LockType;
import io.ruin.model.inter.dialogue.MessageDialogue;
import io.ruin.model.inter.dialogue.OptionsDialogue;
import io.ruin.model.inter.utils.Option;
import io.ruin.model.map.MapListener;
import io.ruin.model.map.Position;
import io.ruin.model.map.Tile;
import io.ruin.model.map.object.actions.ObjectAction;
import io.ruin.model.stat.StatType;
import io.ruin.utility.Misc;
import org.checkerframework.checker.units.qual.A;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;

public class Nex  {

    public static boolean Fumus = false;
    public static boolean Umbra = false;
    public static boolean Cruor = false;
    public static boolean Glacies = false;

    public static boolean FumusDead = false;
    public static boolean UmbraDead = false;
    public static boolean CruorDead = false;
    public static boolean GlaciesDead = false;

    public static ArrayList<Player> deadplayers = new ArrayList<>();

    public static NPC Nex = new NPC(14647).spawn(2909, 4706, 1);

    //Fumus NPC 14651 Cannot be attacked until nex's HP <= 80% (Smoke)
    //Umbra NPC 14652 Cannot be attacked until nex's HP <= 60% (Shadow)
    //Cruor NPC 14653 Cannot be attacked until nex's HP <= 40% (Blood)
    //Glacies NPC 14654 Cannot be attacked until nex's HP <= 20% (Ice)

}
