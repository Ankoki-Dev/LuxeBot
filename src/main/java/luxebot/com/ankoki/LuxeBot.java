package luxebot.com.ankoki;

import luxebot.com.ankoki.commands.GithubCommand;
import luxebot.com.ankoki.commands.RulesCommand;
import luxebot.com.ankoki.gitignore.Secrets;
import luxebot.com.ankoki.listeners.CommandListener;
import luxebot.com.ankoki.managers.GuildCommand;
import luxebot.com.ankoki.managers.PrivateCommand;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.requests.GatewayIntent;

import javax.security.auth.login.LoginException;
import java.util.ArrayList;
import java.util.List;

public class LuxeBot extends ListenerAdapter {

    private static final List<GuildCommand> GUILD_COMMANDS = new ArrayList<>();
    private static final List<PrivateCommand> PRIVATE_COMMANDS = new ArrayList<>();
    private static JDA jda;

    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        System.out.println("Bot Starting!");

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
                    .setStatus(OnlineStatus.IDLE)
                    .build();
        } catch (LoginException ex) {
            ex.printStackTrace();
        }

        registerGuildCommands();
        System.out.printf("Bot was enabled in %.2f seconds!%n", (float) System.currentTimeMillis() - start);
        System.out.println("Invite LuxeBot through this link: " + Secrets.BOT_INVITE);
    }

    private static void registerGuildCommands() {
        GUILD_COMMANDS.add(new RulesCommand());
        GUILD_COMMANDS.add(new GithubCommand());
    }

    public static List<GuildCommand> getGuildCommands() {
        return GUILD_COMMANDS;
    }

    public static List<PrivateCommand> getPrivateCommands() {
        return PRIVATE_COMMANDS;
    }

}
