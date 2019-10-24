/*
 * MIT License
 *
 * Copyright (c) 2019 Glare
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package me.glaremasters.guilds.configuration;

import ch.jalu.configme.SettingsManager;
import ch.jalu.configme.SettingsManagerBuilder;
import ch.jalu.configme.migration.PlainMigrationService;
import me.glaremasters.guilds.Guilds;

import java.io.File;

/**
 * Created by Glare
 * Date: 5/15/2019
 * Time: 4:47 PM
 */
public class SettingsHandler {

    private Guilds guilds;
    private SettingsManager settingsManager;
    private SettingsManager tierSettings;
    private SettingsManager roleSettings;
    private SettingsManager buffSettings;

    public SettingsHandler(Guilds guilds) {

        this.guilds = guilds;

        settingsManager = SettingsManagerBuilder
                .withYamlFile(new File(guilds.getDataFolder(), "config.yml"))
                .migrationService(new GuildsMigrationService(guilds.getDataFolder()))
                .configurationData(GuildConfigurationBuilder.buildConfigurationData())
                .create();

        tierSettings = SettingsManagerBuilder.withYamlFile(new File(guilds.getDataFolder(), "tiers.yml"))
                .migrationService(new PlainMigrationService())
                .configurationData(GuildConfigurationBuilder.buildTierData())
                .create();

        roleSettings = SettingsManagerBuilder.withYamlFile(new File(guilds.getDataFolder(), "roles.yml"))
                .migrationService(new PlainMigrationService())
                .configurationData(GuildConfigurationBuilder.buildRoleData())
                .create();

        buffSettings = SettingsManagerBuilder.withYamlFile(new File(guilds.getDataFolder(), "buffs.yml"))
                .migrationService(new PlainMigrationService())
                .configurationData(GuildConfigurationBuilder.buildBuffData())
                .create();
    }

    public SettingsManager getSettingsManager() {
        return this.settingsManager;
    }

    public SettingsManager getTierSettings() {
        return tierSettings;
    }

    public SettingsManager getRoleSettings() {
        return roleSettings;
    }

    public SettingsManager getBuffSettings() {
        return buffSettings;
    }
}
