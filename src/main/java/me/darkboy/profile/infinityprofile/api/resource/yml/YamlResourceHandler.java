/*
 * Copyright 2018 Bradley Steele
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package me.darkboy.profile.infinityprofile.api.resource.yml;

import me.darkboy.profile.infinityprofile.api.resource.Extension;
import me.darkboy.profile.infinityprofile.api.resource.ResourceHandler;
import me.darkboy.profile.infinityprofile.api.resource.ResourceProvider;
import me.darkboy.profile.infinityprofile.api.resource.ResourceReference;
import me.darkboy.profile.infinityprofile.api.util.logging.StaticLog;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * @author Bradley Steele
 */
public class YamlResourceHandler implements ResourceHandler<ResourceYaml> {

    @Override
    public ResourceYaml load(ResourceProvider provider, ResourceReference reference) {
        ResourceYaml resource = new ResourceYaml(new File(provider.getDataFolder() + reference.getSeparatorPathStart(), reference.getChild()), reference, this);
        resource.setConfiguration(YamlConfiguration.loadConfiguration(resource.getFile()));

        return resource;
    }

    @Override
    public void save(ResourceYaml resource) {
        try {
            resource.getRootConfigurationSection().save(resource.getFile());
        } catch (IOException e) {
            StaticLog.error(String.format("An IOException occurred when trying to save &c%s&r:", resource.getReference()));
            StaticLog.exception(e);
        }
    }

    @Override
    public List<? extends CharSequence> getExtensions() {
        return Extension.YML.getExtensions();
    }
}