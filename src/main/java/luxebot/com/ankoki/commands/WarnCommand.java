package luxebot.com.ankoki.commands;

import luxebot.com.ankoki.managers.GuildCommand;
import luxebot.com.ankoki.utilities.Embed;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.entities.User;
import org.jetbrains.annotations.Nullable;

public class WarnCommand implements GuildCommand {

    @Override
    public void onCommand(Guild guild, User user, TextChannel channel, String[] args, Message message) {
        if (message.getMentionedUsers().size() != 1) {
            channel.sendMessage(Embed.simple(":no_entry_sign: You can only warn one user at a time " + user.getAsTag() + "!" ,user));
            return;
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
