package com.codflix.backend.features.media;

import com.codflix.backend.core.Template;
import com.codflix.backend.models.Genre;
import com.codflix.backend.models.Media;
import com.codflix.backend.features.genre.GenreDao;
import spark.Request;
import spark.Response;
import spark.Session;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MediaController {
    private final MediaDao mediaDao = new MediaDao();
   // private final GenreDao genreDao = new GenreDao();

    public String list(Request request, Response response) {
        List<Media> medias;
       // List<Genre> genres;

        String title = request.queryParams("titl");
       // String genre = request.queryParams("genr");

        if (title != null && !title.isEmpty()) {
            medias = mediaDao.filterMedias(title);
          //  genres = genreDao.filterGenre(genre);

        } else {
            medias = mediaDao.getAllMedias();
          //  genres = genreDao.getAllGenres();
        }

        Map<String, Object> model = new HashMap<>();
       // Map<String, Object> modelgenre = new HashMap<>();
        model.put("medias", medias);
       // modelgenre.put("genres", genres);
        return Template.render("media_list.html", model);
    }

    public String detail(Request request, Response res) {
        int id = Integer.parseInt(request.params(":id"));
        Media media = mediaDao.getMediaById(id);

        Map<String, Object> model = new HashMap<>();
        model.put("media", media);
        return Template.render("media_detail.html", model);
    }
}
