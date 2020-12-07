package luxebot.com.ankoki.commands.chatcontrol.user;

import luxebot.com.ankoki.gitignore.Secrets;
import luxebot.com.ankoki.managers.GuildCommand;
import luxebot.com.ankoki.utilities.Embed;
import luxebot.com.ankoki.utilities.StringUtils;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.*;
import org.jetbrains.annotations.Nullable;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

public class BanCommand implements GuildCommand {

    DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss, dd/MM/yyyy");

    @Override
    public void onCommand(Guild guild, User user, TextChannel channel, String[] args, Message message) {
        if (message.getMentionedUsers().size() != 1) {
            channel.sendMessage(Embed.simple(":no_entry_sign: Incorrect Usage. Use this format: `" + Secrets.PREFIX + "ban <user> <reason>`" ,user)).queue();
            return;
        }
        if (args.length <= 1) {
            channel.sendMessage(Embed.simple(":no_entry_sign: Incorrect Usage. Use this format: `" + Secrets.PREFIX + "ban <user> <reason>`" ,user)).queue();
            return;
        }
        for (User banned : message.getMentionedUsers()) {
            if (banned.isBot()) {
                channel.sendMessage(Embed.simple(":no_entry_sign: You cannot ban bots " + user.getName() + "!", user)).queue();
                return;
            }
            Member bannedMember = guild.getMember(banned);
            if (bannedMember.isOwner()) {
                channel.sendMessage(Embed.simple(":no_entry_sign: You cannot ban the owner " + user.getName() + "!", user)).queue();
                return;
            }
            banned.openPrivateChannel().queue(channel1 -> {
                channel1.sendMessage(new MessageEmbed(null, "You have been warned!",
                        null, null, null, Secrets.COLOR.getRGB(), null,
                        null ,null, null, new MessageEmbed.Footer(user.getAsTag() + " | " + dateFormat.format(new Date()), null, null), null,
                        Arrays.asList(new MessageEmbed.Field("You were warned by " + user.getAsTag() + "!",
                                "You were banned for `" + StringUtils.arrayAsString(StringUtils.removeOne(args)) +
                                        "`, to appeal, you my message the person that banned you and query as to why they took this action!", false)))).queue();
            });
            guild.ban(user, 0).queue();
            channel.sendMessage(Embed.simple(user.getAsTag() + " has banned " + banned.getAsTag() + " for `" + StringUtils.arrayAsString(StringUtils.removeOne(args)) + "`!", user)).queue();
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
