package com.ankoki.luxebot.commands.chatcontrol.user;

import com.ankoki.luxebot.gitignore.Secrets;
import com.ankoki.luxebot.utilities.StringUtils;
import com.ankoki.luxebot.managers.GuildCommand;
import com.ankoki.luxebot.utilities.Embed;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.*;
import org.jetbrains.annotations.Nullable;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

public class KickCommand implements GuildCommand {

    DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss, dd/MM/yyyy");

    @Override
    public void onCommand(Guild guild, User user, TextChannel channel, String[] args, Message message) {
        if (message.getMentionedUsers().size() != 1) {
            channel.sendMessage(Embed.simple(":no_entry_sign: Incorrect Usage. Use this format: `" + Secrets.PREFIX + "kick <user> <reason>`" ,user)).queue();
            return;
        }
        if (args.length <= 1) {
            channel.sendMessage(Embed.simple(":no_entry_sign: Incorrect Usage. Use this format: `" + Secrets.PREFIX + "kick <user> <reason>`" ,user)).queue();
            return;
        }
        for (User kicked : message.getMentionedUsers()) {
            if (kicked.isBot()) {
                channel.sendMessage(Embed.simple(":no_entry_sign: You cannot kick bots " + user.getName() + "!", user)).queue();
                return;
            } else if (guild.getMember(kicked).isOwner()) {
                channel.sendMessage(Embed.simple(":no_entry_sign: You cannot kick the owner! " + user.getName() + "!", user)).queue();
                return;
            }
            kicked.openPrivateChannel().queue(channel1 -> {
                channel1.sendMessage(new MessageEmbed(null, "You have been warned!",
                        null, null, null, Secrets.COLOR.getRGB(), null,
                        null ,null, null, new MessageEmbed.Footer(user.getAsTag() + " | " + dateFormat.format(new Date()), null, null), null,
                        Arrays.asList(new MessageEmbed.Field("You were warned by " + user.getAsTag() + "!",
                                "You were kicked for `" + StringUtils.arrayAsString(StringUtils.removeOne(args)) +
                                        "`, you may join back, however please be wary about doing this type of action again, as it may lead to a ban!", false)))).queue();
            });
            guild.kick(user.getId()).queue();
            channel.sendMessage(Embed.simple(user.getAsTag() + " has kicked " + kicked.getAsTag() + " for `" + StringUtils.arrayAsString(StringUtils.removeOne(args)) + "`!", user)).queue();
        }
    }

    @Override
    public String[] getAliases() {
        return new String[]{"ban"};
    }

    @Override
    @Nullable
    public Permission[] getPermissions() {
        return new Permission[]{Permission.BAN_MEMBERS};
    }
}
