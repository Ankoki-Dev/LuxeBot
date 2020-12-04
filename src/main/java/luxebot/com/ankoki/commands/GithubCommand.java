package luxebot.com.ankoki.commands;

import luxebot.com.ankoki.gitignore.Secrets;
import luxebot.com.ankoki.managers.GuildCommand;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.*;
import net.dv8tion.jda.api.entities.MessageEmbed.Footer;
import net.dv8tion.jda.api.entities.MessageEmbed.Field;
import org.jetbrains.annotations.Nullable;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

public class GithubCommand implements GuildCommand {

    DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss, dd/MM/yyyy");

    private MessageEmbed GITHUB_EMBED(User user, Date date) {
        return new MessageEmbed("https://github.com/Ankoki-Dev/", "Ankoki-Dev", null, null, null,
                Secrets.COLOR.getRGB(), null, null, null, null,
                new Footer(user.getName() + " | " + dateFormat.format(date), null, null), null,
                Arrays.asList(new Field("Follow Ankoki-Dev on GitHub",
                        "Visit Ankoki-Dev on GitHub to kep up to date on all their latest " +
                        "repositories and projects that they may be working on!", false),
                        new Field("Info",
                                "Most work Ankoki does on the server is published " +
                                "here, even this bot!", false)));
    }

    @Override
    public void onCommand(Guild guild, User user, TextChannel channel, String[] args, Message message) {
        channel.sendMessage(GITHUB_EMBED(user, new Date())).queue();
    }

    @Override
    public String[] getAliases() {
        return new String[]{"github", "git"};
    }

    @Override
    @Nullable
    public Permission[] getPermissions() {
        return null;
    }
}
