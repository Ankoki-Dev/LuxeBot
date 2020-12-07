package com.ankoki.luxebot.commands.help;

import com.ankoki.luxebot.gitignore.Secrets;
import com.ankoki.luxebot.managers.GuildCommand;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.*;
import net.dv8tion.jda.api.entities.MessageEmbed.Footer;
import net.dv8tion.jda.api.entities.MessageEmbed.Field;
import org.jetbrains.annotations.Nullable;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

public class RulesCommand implements GuildCommand {

    DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss, dd/MM/yyyy");

    private MessageEmbed RULES_EMBED(User user, Date date) {
        return new MessageEmbed(null, "LuxeBlock Rules",
                null, null, null, Secrets.COLOR.getRGB(),
                null, null, null, null,
                new Footer(user.getName() + " | " + dateFormat.format(date) + " GMT", null, null), null,
                Arrays.asList(new Field("[Rules]", "These are the rules you need to follow " +
                                "to be able to stay in this discord and this community " +
                                "as we want to keep this a safe space.", false),
                        new Field("**1.** No Advertising.",
                                "This one is a given. If you advertise, you will be " +
                                "removed from the discord, simple.", false),
                        new Field("**2.** No Toxic Chat.",
                                "If you are toxic to anyone in a harmful or intended " +
                                "manner, you will be muted as this is not tolerated. " +
                                "As stated before, this is a safe space for people.", false),
                        new Field("**3.** No Slurs.",
                                "If you are found to use any slurs here, you will be " +
                                "removed. Even if you are able to reclaim this slur, we " +
                                "are unable to be sure of this, so people will be " +
                                "punished regardless. We apologise for this.", false),
                        new Field("**4.** No Spamming.",
                                "Spamming is just annoying. Nobody enjoys seeing a giant " +
                                "block of shit. Literally nobody. So please don't. " +
                                "Again, this will result in a mute.", false),
                        new Field("**5.** In Regards to Help.",
                                "Of course people here will help you with whatever issues " +
                                "you are having, whether they are related to the server or not. " +
                                "However, please understand the basics of what you are doing. " +
                                "We cannot assist people who do not understand the very basics " +
                                "of their subject.", false),
                        new Field("**6.** Have Fun!",
                                "At the end of the day, this server is here for fun, and we " +
                                "all just want to have fun, so just chill and yeah that's " +
                                "just about everything! Enjoy:)", false),
                        new Field("**Punishments**",
                                "Actions have consequences, and if you break any of the " +
                                "rules stated above, you could be muted, banned, or kicked " +
                                "from the server, and we don't want to have to do that, so " +
                                "please follow the rules and listen to what staff say<3", false),
                        new Field("***Ankoki***",
                                "My DM's are always open if anyone has any questions, " +
                                "and I'm quite active, so if you ever have a problem, don't be " +
                                "afraid to message me:) - Ankoki#0001", false)));
    }

    @Override
    public void onCommand(Guild guild, User user, TextChannel channel, String[] args, Message message) {
        channel.sendMessage(RULES_EMBED(user, new Date())).queue();
    }

    @Override
    public String[] getAliases() {
        return new String[]{"rules", "rule"};
    }

    @Override
    @Nullable
    public Permission[] getPermissions() {
        return null;
    }
}
