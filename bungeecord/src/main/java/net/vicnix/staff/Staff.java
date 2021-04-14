package net.vicnix.staff;

import com.google.common.io.ByteStreams;
import net.md_5.bungee.api.plugin.Plugin;
import net.md_5.bungee.config.Configuration;
import net.md_5.bungee.config.ConfigurationProvider;
import net.md_5.bungee.config.YamlConfiguration;
import net.vicnix.staff.listener.PlayerDisconnectListener;
import net.vicnix.staff.listener.PostLoginListener;
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

        ConsoleUtils.setInstance(new BungeeConsoleUtils());

        try {
            this.saveConfig();

            Configuration config = ConfigurationProvider.getProvider(YamlConfiguration.class).load(this.file);

            MongoDBProvider.getInstance().init(config.getString("mongouri"));

            this.getProxy().getPluginManager().registerListener(this, new PostLoginListener());
            this.getProxy().getPluginManager().registerListener(this, new PlayerDisconnectListener());

            //this.getProxy().getPluginManager().registerCommand(this, null);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void saveConfig() throws IOException {
        if (!this.getDataFolder().exists()) {
            this.getDataFolder().mkdir();
        }

        if (file.exists() || !file.createNewFile()) return;

        try (InputStream is = this.getResourceAsStream("config.yml");
             OutputStream os = new FileOutputStream(this.file)) {
            ByteStreams.copy(is, os);
        }
    }
}