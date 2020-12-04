package luxebot.com.ankoki;

import luxebot.com.ankoki.gitignore.Secrets;
import luxebot.com.ankoki.listeners.CommandListener;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.requests.GatewayIntent;

import javax.security.auth.login.LoginException;
import java.util.ArrayList;

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
                    .setActivity(Activity.watching("out for seleener"))
                    .setStatus(OnlineStatus.IDLE)
                    .setIdle(true)
                    .build();
        } catch (LoginException ex) {
            ex.printStackTrace();
        }

        //registerCommands();
        System.out.printf("Bot was enabled in %.2f seconds!%n", (float) System.currentTimeMillis() - start);
        System.out.println("Invite ch0k3r through this link: " + Secrets.BOT_INVITE);
    }
}
