package luxebot.com.ankoki.commands;

import luxebot.com.ankoki.managers.GuildCommand;
import luxebot.com.ankoki.utilities.Embed;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.entities.User;
import org.jetbrains.annotations.Nullable;

public class IPCommand implements GuildCommand {

    @Override
    public void onCommand(Guild guild, User user, TextChannel channel, String[] args, Message message) {
        channel.sendMessage(Embed.simple(":no_entry_sign: Um... awkward but we don't have any IP's right now...", user)).queue();
    }

    @Override
    public String[] getAliases() {
        return new String[]{"ip", "join"};
    }

    @Override
    @Nullable
    public Permission[] getPermissions() {
        return null;
    }
}
