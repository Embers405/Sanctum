package io.ruin.services.discord;

import io.ruin.model.World;
import io.ruin.process.task.TaskWorker;
//import io.ruin.services.discord.impl.PrivateMessageReceived;
import io.ruin.services.discord.impl.UserCommands;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import javax.security.auth.login.LoginException;

public class DiscordConnection extends ListenerAdapter {

	public static JDA jda;

	String token = "MTE3MjM5OTQwMTM3MzgxNDkwOA.GVb0cJ.rpjwRzOEub9P8sbMXSfVEyU4w16SmnjKPaiP2w";

	public static final String CHANNEL_PUNISHMENTS = "902658112614432819";

	public void initialize() {
		JDABuilder builder = JDABuilder.createDefault(token);
		builder.setStatus(OnlineStatus.ONLINE);
		builder.addEventListeners(this);
		builder.addEventListeners(new UserCommands());
//		builder.addEventListeners(new AdminCommands());
		//builder.addEventListeners(new PrivateMessageReceived());
		try {
			jda = builder.build();
			TaskWorker.startTask(t -> {
				while (true) {
					t.sleep(60000L);
					jda.getPresence().setPresence(OnlineStatus.ONLINE, Activity.playing("Sanctum with " + World.players.count() + " players!"));
				}
			});
		} catch (LoginException e) {
			e.printStackTrace();
		}

	}

}