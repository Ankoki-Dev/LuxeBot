package com.ankoki.luxebot.commands.help;

import com.ankoki.luxebot.managers.GuildCommand;
import com.ankoki.luxebot.utilities.Embed;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.entities.User;
import org.jetbrains.annotations.Nullable;

public class PastebinCommand implements GuildCommand {

    @Override
    public void onCommand(Guild guild, User user, TextChannel channel, String[] args, Message message) {
        channel.sendMessage(Embed.simple("**Pastebin Websites:**\n:one: https://hastebin.com/\n:two: https://paste.md-5.net/\n:three: https://pastebin.com/", user)).queue();
    }

    @Override
    public String[] getAliases() {
        return new String[]{"paste", "haste", "pb", "hb"};
    }

    @Override
    @Nullable
    public Permission[] getPermissions() {
        return null;
    }
}
