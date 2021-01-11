package com.ankoki.luxebot;

import com.ankoki.luxebot.commands.fun.SucksCommand;
import com.ankoki.luxebot.gitignore.Secrets;
import com.ankoki.luxebot.commands.chatcontrol.PurgeCommand;
import com.ankoki.luxebot.commands.fun.EightBallCommand;
import com.ankoki.luxebot.commands.help.RulesCommand;
import com.ankoki.luxebot.commands.fun.BottomCommand;
import com.ankoki.luxebot.commands.help.GithubCommand;
import com.ankoki.luxebot.commands.help.IPCommand;
import com.ankoki.luxebot.commands.help.PastebinCommand;
import com.ankoki.luxebot.commands.chatcontrol.user.BanCommand;
import com.ankoki.luxebot.commands.chatcontrol.user.KickCommand;
import com.ankoki.luxebot.commands.chatcontrol.user.WarnCommand;
import com.ankoki.luxebot.listeners.CommandListener;
import com.ankoki.luxebot.managers.GuildCommand;
import com.ankoki.luxebot.managers.PrivateCommand;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.utils.MemberCachePolicy;

import javax.security.auth.login.LoginException;
import java.util.ArrayList;
import java.util.List;

public class LuxeBot extends ListenerAdapter {

    private final List<GuildCommand> GUILD_COMMANDS = new ArrayList<>();
    private final List<PrivateCommand> PRIVATE_COMMANDS = new ArrayList<>();
    private static LuxeBot instance;
    private static JDA jda;

    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        System.out.println("Bot Starting!");

        instance = new LuxeBot();

        String token = Secrets.TOKEN;

        try {
            jda = JDABuilder.createDefault(token).build();
        } catch (LoginException ex) {
            ex.printStackTrace();
            return;
        }

        try {
            jda = jda.awaitReady();
        } catch (InterruptedException ex) {
            ex.printStackTrace();
            return;
        }

        try {
            JDABuilder.createLight(token, GatewayIntent.GUILD_MESSAGES, GatewayIntent.DIRECT_MESSAGES, GatewayIntent.GUILD_MESSAGE_REACTIONS, GatewayIntent.DIRECT_MESSAGE_REACTIONS, GatewayIntent.GUILD_BANS, GatewayIntent.GUILD_MEMBERS)
                    .addEventListeners(new CommandListener(jda))
                    .setActivity(Activity.listening("Ayesha Erotica"))
                    .setMemberCachePolicy(MemberCachePolicy.ALL)
                    .enableIntents(GatewayIntent.GUILD_MEMBERS)
                    .setStatus(OnlineStatus.DO_NOT_DISTURB)
                    .build();
        } catch (LoginException ex) {
            ex.printStackTrace();
        }

        instance.registerGuildCommands();
        System.out.printf("Bot was enabled in %.2f seconds!%n", (float) System.currentTimeMillis() - start);
        System.out.println("Invite LuxeBot through this link: " + Secrets.BOT_INVITE);
    }

    private void registerGuildCommands() {
        GUILD_COMMANDS.add(new RulesCommand());
        GUILD_COMMANDS.add(new GithubCommand());
        GUILD_COMMANDS.add(new WarnCommand());
        GUILD_COMMANDS.add(new PurgeCommand());
        GUILD_COMMANDS.add(new IPCommand());
        GUILD_COMMANDS.add(new BanCommand());
        GUILD_COMMANDS.add(new KickCommand());
        GUILD_COMMANDS.add(new BottomCommand());
        GUILD_COMMANDS.add(new PastebinCommand());
        GUILD_COMMANDS.add(new EightBallCommand());
        GUILD_COMMANDS.add(new SucksCommand());
    }

    public static LuxeBot instance() {
        return instance;
    }

    public List<GuildCommand> getGuildCommands() {
        return GUILD_COMMANDS;
    }

    public List<PrivateCommand> getPrivateCommands() {
        return PRIVATE_COMMANDS;
    }

}
