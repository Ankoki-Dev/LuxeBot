package luxebot.com.ankoki.listeners;

import luxebot.com.ankoki.gitignore.Secrets;
import luxebot.com.ankoki.managers.GuildCommand;
import luxebot.com.ankoki.managers.PrivateCommand;
import luxebot.com.ankoki.utilities.Embed;
import luxebot.com.ankoki.utilities.StringUtils;
import luxebot.com.ankoki.LuxeBot;
import luxebot.com.ankoki.utilities.UserUtils;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.events.message.priv.PrivateMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.util.Arrays;

public class CommandListener extends ListenerAdapter {

    JDA jda;

    public CommandListener(JDA jda) {
        this.jda = jda;
    }

    @Override
    public void onGuildMessageReceived(GuildMessageReceivedEvent e) {
        String message = e.getMessage().getContentRaw();
        if (e.getAuthor().isBot()) return;
        if (!message.startsWith(Secrets.PREFIX)) return;

        String commandName = StringUtils.getCommandName(e.getMessage().getContentRaw(), Secrets.PREFIX);
        for (GuildCommand command : LuxeBot.getGuildCommands()) {
            if (Arrays.asList(command.getAliases()).contains(commandName.toLowerCase())) {
                if (UserUtils.hasPerm(e.getMember(), command.getPermissions())) {
                    command.onCommand(e.getGuild(), e.getAuthor(), e.getChannel(), StringUtils.withoutCommand(message).split(" "), e.getMessage());
                    break;
                } else {
                    e.getChannel().sendMessage(Embed.noPermission(e.getAuthor())).queue();
                }
                break;
            }
        }
    }

    @Override
    public void onPrivateMessageReceived(PrivateMessageReceivedEvent e) {
        String message = e.getMessage().getContentRaw();
        if (e.getAuthor().isBot()) return;
        if (!message.startsWith(Secrets.PREFIX)) return;

        String commandName = StringUtils.getCommandName(e.getMessage().getContentRaw(), Secrets.PREFIX);
        for (PrivateCommand command : LuxeBot.getPrivateCommands()) {
            if (Arrays.asList(command.getAliases()).contains(commandName.toLowerCase())) {
                command.onCommand(e.getAuthor(), e.getChannel(), StringUtils.withoutCommand(message).split(" "), commandName);
                break;
            }
        }
    }
}