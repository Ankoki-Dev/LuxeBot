package luxebot.com.ankoki.commands;

import luxebot.com.ankoki.gitignore.Secrets;
import luxebot.com.ankoki.managers.GuildCommand;
import luxebot.com.ankoki.utilities.Embed;
import luxebot.com.ankoki.utilities.StringUtils;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.*;
import net.dv8tion.jda.api.entities.MessageEmbed.Field;
import net.dv8tion.jda.api.entities.MessageEmbed.Footer;
import org.jetbrains.annotations.Nullable;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

public class WarnCommand implements GuildCommand {

    DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss, dd/MM/yyyy");

    @Override
    public void onCommand(Guild guild, User user, TextChannel channel, String[] args, Message message) {
        if (message.getMentionedUsers().size() != 1) {
            channel.sendMessage(Embed.simple(":no_entry_sign: Incorrect Usage. Use this format: `" + Secrets.PREFIX + "warn <user> <reason>`" ,user)).queue();
            return;
        }
        if (args.length <= 1) {
            channel.sendMessage(Embed.simple(":no_entry_sign: Incorrect Usage. Use this format: `" + Secrets.PREFIX + "warn <user> <reason>`" ,user)).queue();
            return;
        }
        for (User warned : message.getMentionedUsers()) {
            if (warned.isBot()) {
                channel.sendMessage(Embed.simple(":no_entry_sign: You cannot warn bots " + user.getName() + "!", user)).queue();
                return;
            }
            warned.openPrivateChannel().queue(channel1 -> {
                channel1.sendMessage(new MessageEmbed(null, "You have been warned!",
                        null, null, null, Secrets.COLOR.getRGB(), null,
                        null ,null, null, new Footer(user.getAsTag() + " | " + dateFormat.format(new Date()), null, null), null,
                        Arrays.asList(new Field("You were warned by " + user.getAsTag() + "!",
                                "You were warned for `" + StringUtils.asString(StringUtils.removeOne(args)) +
                                        "`, please refrain from doing this again, as it may end in a ban!", false)))).queue();
            });
            channel.sendMessage(Embed.simple(user.getAsTag() + " has warned " + warned.getAsTag() + " for `" + StringUtils.asString(StringUtils.removeOne(args)) + "`!", user)).queue();
        }
    }

    @Override
    public String[] getAliases() {
        return new String[]{"warn"};
    }

    @Override
    @Nullable
    public Permission[] getPermissions() {
        return new Permission[]{Permission.KICK_MEMBERS, Permission.BAN_MEMBERS};
    }
}
