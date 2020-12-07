package com.ankoki.luxebot.managers;

import net.dv8tion.jda.api.entities.*;

public interface PrivateCommand {
    void onCommand(User user, PrivateChannel channel, String[] args, String alias);
    String[] getAliases();
    default void onReaction(User reactor, Message message, MessageReaction.ReactionEmote emote){}
}