package com.ankoki.luxebot.utilities;

import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Member;
import org.jetbrains.annotations.Nullable;

public class UserUtils {

    public static boolean hasPerm(Member member, @Nullable Permission[] permissions) {
        if (permissions == null || member.isOwner() || member.hasPermission(Permission.ADMINISTRATOR)) {
            return true;
        }
        for (Permission perm : permissions) {
            if (member.hasPermission(perm)) {
                return true;
            }
        }
        return false;
    }

}
