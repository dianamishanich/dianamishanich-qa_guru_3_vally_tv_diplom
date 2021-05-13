package helpers;

import org.aeonbits.owner.Config;

    @Config.LoadPolicy(Config.LoadType.MERGE)
    @Config.Sources({
            "file:../secret1.properties",
    })

    public interface UserVariables extends Config {

        @Key("user.login")
        String userLogin();

        @Key("user.password")
        String userPassword();

}
