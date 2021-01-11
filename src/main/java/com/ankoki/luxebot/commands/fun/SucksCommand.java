package com.ankoki.luxebot.commands.fun;

import com.ankoki.luxebot.managers.GuildCommand;
import com.ankoki.luxebot.utilities.Embed;
import com.ankoki.luxebot.utilities.StringUtils;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.entities.User;
import org.jetbrains.annotations.Nullable;

public class SucksCommand implements GuildCommand {

    @Override
    public void onCommand(Guild guild, User user, TextChannel channel, String[] args, Message message) {
        channel.sendMessage(Embed.simple(StringUtils.arrayAsString(args) + " sucks by the way", user)).queue();
    }

    @Override
    public String[] getAliases() {
        return new String[]{"sucks"};
    }

    @Override
    public @Nullable Permission[] getPermissions() {
        return new Permission[]{Permission.MESSAGE_MANAGE};
    }
}
