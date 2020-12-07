package com.ankoki.luxebot.commands.fun;

import com.ankoki.luxebot.utilities.StringUtils;
import com.ankoki.luxebot.managers.GuildCommand;
import com.ankoki.luxebot.utilities.Embed;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.entities.User;
import org.jetbrains.annotations.Nullable;

public class BottomCommand implements GuildCommand {

    @Override
    public void onCommand(Guild guild, User user, TextChannel channel, String[] args, Message message) {
        channel.sendMessage(Embed.simple(StringUtils.arrayAsString(args) + " is a bottom", user)).queue();
    }

    @Override
    public String[] getAliases() {
        return new String[]{"bottom"};
    }

    @Override
    @Nullable
    public Permission[] getPermissions() {
        return null;
    }
}
