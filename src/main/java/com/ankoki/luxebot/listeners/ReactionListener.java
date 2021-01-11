package com.ankoki.luxebot.listeners;

import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.message.guild.react.GuildMessageReactionAddEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.lang.reflect.Member;

//Not registered as a listener yet
public class ReactionListener extends ListenerAdapter {

    @Override
    public void onGuildMessageReactionAdd(GuildMessageReactionAddEvent e) {
        User user = e.getUser();
        if (user.isBot()) return;
    }
}
