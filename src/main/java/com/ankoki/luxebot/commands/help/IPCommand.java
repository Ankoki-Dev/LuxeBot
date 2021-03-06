package com.ankoki.luxebot.commands.help;

import com.ankoki.luxebot.managers.GuildCommand;
import com.ankoki.luxebot.utilities.Embed;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.entities.User;
import org.jetbrains.annotations.Nullable;

public class IPCommand implements GuildCommand {

    @Override
    public void onCommand(Guild guild, User user, TextChannel channel, String[] args, Message message) {
        channel.sendMessage(Embed.simple(":no_entry_sign: theluxe.mcserver.us is our IP, however, you will get kicked upon joining for the time being!", user)).queue();
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
