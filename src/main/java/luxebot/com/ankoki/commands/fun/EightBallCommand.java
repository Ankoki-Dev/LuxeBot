package luxebot.com.ankoki.commands.fun;

import luxebot.com.ankoki.managers.GuildCommand;
import luxebot.com.ankoki.utilities.Embed;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.entities.User;
import org.jetbrains.annotations.Nullable;

import java.util.Random;

public class EightBallCommand implements GuildCommand {

    String[] answers = {
        ":crystal_ball: Indeed...",
        ":crystal_ball: Not that I can see...",
        ":crystal_ball: For sure!",
        ":crystal_ball: Maybe?",
        ":crystal_ball: It's not certain...",
        ":crystal_ball: It's certain!",
        ":crystal_ball: Yes",
        ":crystal_ball: No"
    };

    @Override
    public void onCommand(Guild guild, User user, TextChannel channel, String[] args, Message message) {
        int randInt = new Random().nextInt(8);
        channel.sendMessage(Embed.simple(answers[randInt], user)).queue();
    }

    @Override
    public String[] getAliases() {
        return new String[]{"8ball", "eightball"};
    }

    @Override
    @Nullable
    public Permission[] getPermissions() {
        return new Permission[0];
    }
}
