package com.ankoki.luxebot.listeners;

import com.ankoki.luxebot.LuxeBot;
import com.ankoki.luxebot.gitignore.Secrets;
import com.ankoki.luxebot.managers.GuildCommand;
import com.ankoki.luxebot.managers.PrivateCommand;
import com.ankoki.luxebot.utilities.Embed;
import com.ankoki.luxebot.utilities.StringUtils;
import com.ankoki.luxebot.utilities.UserUtils;
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
        for (GuildCommand command : LuxeBot.instance().getGuildCommands()) {
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
        for (PrivateCommand command : LuxeBot.instance().getPrivateCommands()) {
            if (Arrays.asList(command.getAliases()).contains(commandName.toLowerCase())) {
                command.onCommand(e.getAuthor(), e.getChannel(), StringUtils.withoutCommand(message).split(" "), commandName);
                break;
            }
        }
    }
}