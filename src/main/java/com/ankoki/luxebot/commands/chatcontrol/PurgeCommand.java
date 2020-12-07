package com.ankoki.luxebot.commands.chatcontrol;

import com.ankoki.luxebot.utilities.StringUtils;
import com.ankoki.luxebot.managers.GuildCommand;
import com.ankoki.luxebot.utilities.Embed;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.*;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class PurgeCommand implements GuildCommand {

    boolean isWorking;

    @Override
    public void onCommand(Guild guild, User user, TextChannel channel, String[] args, Message message) {
        if (isWorking) {
            channel.sendMessage(PURGE_FAILED_EMBED("I am currently working! Please wait...", user)).queue();
            return;
        }
        purge(channel, StringUtils.arrayAsString(args), user);
    }

    @Override
    public String[] getAliases() {
        return new String[]{"purge", "remove"};
    }

    @Override
    @Nullable
    public Permission[] getPermissions() {
        return new Permission[]{Permission.MESSAGE_MANAGE};
    }

    private static final MessageEmbed PURGE_SUCCESS_EMBED(int messagesPurged, User user) {
        return Embed.simple(":white_check_mark: You have successfully purged " + messagesPurged + " messages!", user);
    }

    private static final MessageEmbed PURGE_FAILED_EMBED(String reason, User user) {
        return Embed.simple(":no_entry_sign: " + reason, user);
    }

    private void purge(TextChannel channel, String contents, User user) {
        isWorking = true;
        int i;
        String[] stupidWorkaround = contents.split(" ");
        try {
            i = Integer.parseInt(stupidWorkaround[0]) + 1;
        } catch (NumberFormatException ex) {
            channel.sendMessage(PURGE_FAILED_EMBED("Use this command with the format `.purge <number>`!", user)).queue();
            isWorking = false;
            return;
        }
        if (2 > i || i > 100) {
            channel.sendMessage(PURGE_FAILED_EMBED("Invalid number! Please use a number between `2 and 99`!", user)).queue();
            isWorking = false;
            return;
        }
        new Thread(() -> {
            List<Message> messages = channel.getHistory().retrievePast(i).complete();
            messages.remove(0);
            if (messages.isEmpty()) {
                isWorking = false;
                channel.sendMessage(PURGE_FAILED_EMBED("No messages to delete!", user));
            } else {
                channel.deleteMessages(messages).complete();
                isWorking = false;
                channel.sendMessage(PURGE_SUCCESS_EMBED(i - 1, user)).queue();
            }
        }).start();
        isWorking = false;
    }
}
