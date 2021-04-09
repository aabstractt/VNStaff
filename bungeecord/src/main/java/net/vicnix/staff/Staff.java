package net.vicnix.staff;

import com.google.common.io.ByteStreams;
import net.md_5.bungee.api.plugin.Plugin;
import net.md_5.bungee.config.Configuration;
import net.md_5.bungee.config.ConfigurationProvider;
import net.md_5.bungee.config.YamlConfiguration;
import net.vicnix.staff.provider.MongoDBProvider;

import java.io.*;

public class Staff extends Plugin {

    public static Staff instance;

    private final File file = new File(getDataFolder().getPath(), "config.yml");

    public static Staff getInstance() {
        return instance;
    }

    public void onEnable() {
        instance = this;

        this.saveConfig();

        try {
            Configuration config = ConfigurationProvider.getProvider(YamlConfiguration.class).load(this.file);

            MongoDBProvider.getInstance().init(config.getString("mongouri"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void saveConfig() {
        if (!this.getDataFolder().exists()) {
            this.getDataFolder().mkdir();
        }


        if (!file.exists()) {
            try {
                file.createNewFile();
                try (InputStream is = this.getResourceAsStream("config.yml");
                     OutputStream os = new FileOutputStream(this.file)) {
                    ByteStreams.copy(is, os);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}