package anvil.asrepo;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.List;


public class ASRepoManager {

    public static final Gson gson = new GsonBuilder().create();

    public static Repository loadRepo(String url) {
        Repository repository = new Repository();
        repository.baseURL = url.replaceAll("index.json", "");
        String json = NetLoader.readURL(url);
        repository.index = gson.fromJson(json, IndexJSON.class);
        return repository;
    }

    public static RepoEntry loadEntry(IndexJSON.Entry input, Repository repo) {
        RepoEntry entry = new RepoEntry();
        entry.ID = input.id;
        if (input.script != null) {
            entry.scriptURL = input.script;
        } else {
            if (NetLoader.readURL(repo.baseURL + entry.ID + ".json") != "") {
                entry.scriptURL = repo.baseURL + "addons/" + entry.ID + ".json";
            } else {
                entry.scriptURL = repo.baseURL + "addons/" + entry.ID + "/addon.json";
            }
        }

        if (input.meta != null) {
            if (input.meta.external != null) {
                String metajson = NetLoader.readURL(input.meta.external);
                Meta meta = gson.fromJson(metajson, Meta.class);
                entry.Name = meta.name;
                entry.description =meta.description;
                entry.logo = meta.logo;
            } else {
                entry.description = input.meta.description;
                entry.logo= input.meta.logo;
                if (input.meta.name != null) {
                    entry.Name = input.meta.name;
                } else {
                    entry.Name = input.id;
                }
            }
        } else {
            entry.Name = input.id;
        }

        return entry;
    }

    public static List<RepoEntry> loadEntryList(Repository repo) {
        List<RepoEntry> entries = new ArrayList<>();
        for (IndexJSON.Entry entry : repo.index.addons) {
            entries.add(loadEntry(entry, repo));
        }
        return entries;
    }

}
