import com.fasterxml.jackson.core.type.TypeReference;
import com.ll.exam.article.dto.ArticleDto;
import com.ll.exam.util.Ut;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class AppTest {
    @Test
    void assertJ__assertThat() {
        int rs = 10 + 20;
        assertThat(rs).isEqualTo(30);
    }

    @Test
    @DisplayName("ArticleDto -> json")
    void ObjectMapper__ObjectMapper() {
        ArticleDto articleDto = new ArticleDto(1, "제목", "내용");

        String jsonStr = Ut.json.toStr(articleDto, "");
        assertThat(jsonStr).isNotBlank();
        assertThat(jsonStr).isEqualTo("""
                {"id":1,"title":"제목","body":"내용"}
                """.trim());
    }

    @Test
    @DisplayName("json -> ArticleDto")
    void ObjectMapper__jsonStrToObj() {
        ArticleDto articleDtoOrigin = new ArticleDto(1, "제목", "내용");
        String jsonStr = Ut.json.toStr(articleDtoOrigin, "");

        ArticleDto articleDtoFromJson = Ut.json.toObj(jsonStr, ArticleDto.class, null);

        assertThat(articleDtoOrigin).isEqualTo(articleDtoFromJson);
    }

    @Test
    @DisplayName("List -> json")
    void ObjectMapper__listToJson() {
        List<ArticleDto> list = new ArrayList<>();
        list.add(new ArticleDto(1, "title1", "body1"));
        list.add(new ArticleDto(2, "title2", "body2"));

        String jsonStr = Ut.json.toStr(list, "");

        assertThat(jsonStr).isNotBlank();
        assertThat(jsonStr).isEqualTo("""
                [{"id":1,"title":"title1","body":"body1"},{"id":2,"title":"title2","body":"body2"}]
                """.trim());
    }

    @Test
    @DisplayName("Map -> json")
    void ObjectMapper__mapToJson() {
        Map<String, Object> d = new HashMap<String, Object>();
        d.put("recent", new ArticleDto(1, "title1", "body1"));
        d.put("old", new ArticleDto(2, "title2", "body2"));

        String jsonStr = Ut.json.toStr(d, "");

        assertThat(jsonStr).isNotBlank();
        assertThat(jsonStr).isEqualTo("""
                {"old":{"id":2,"title":"title2","body":"body2"},"recent":{"id":1,"title":"title1","body":"body1"}}
                """.trim());
    }

    @Test
    @DisplayName("json -> List<ArticleDto>")
    void ObjectMapper__jsonStrToArticleDtoList() {
        List<ArticleDto> articleDtos = new ArrayList<>();
        articleDtos.add(new ArticleDto(1, "제목1", "내용1"));
        articleDtos.add(new ArticleDto(2, "제목2", "내용2"));

        String jsonStr = Ut.json.toStr(articleDtos, "");

        List<ArticleDto> articleDtosFromJson = Ut.json.toObj(jsonStr, new TypeReference<List<ArticleDto>>() {
        }, null);
//        System.out.println(jsonStr);
//        System.out.println(articleDtosFromJson);
        assertThat(articleDtosFromJson).isEqualTo(articleDtos);
    }

    @Test
    @DisplayName("json -> Map<String, ArticleDto>x")
    void ObjectMapper__jsonStrToArticleDtoMap() {
        Map<String, ArticleDto> articleDtoMap = new HashMap<>();
        articleDtoMap.put("가장오래된", new ArticleDto(1, "제목1", "내용1"));
        articleDtoMap.put("최신", new ArticleDto(2, "제목2", "내용2"));

        String jsonStr = Ut.json.toStr(articleDtoMap, "");

        Map<String, ArticleDto> articleDtoMapFromJson = Ut.json.toObj(jsonStr, new TypeReference<Map<String, ArticleDto>>() {
        }, null);
//        System.out.println(jsonStr);
//        System.out.println(articleDtoMapFromJson);
        assertThat(articleDtoMapFromJson).isEqualTo(articleDtoMap);
    }

    @Test
    void mapOf() {
        Map<String, Object> map = Ut.mapOf("age", 11, "name", "Paul");

        assertThat(map.get("age")).isEqualTo(11);
        assertThat(map.get("name")).isEqualTo("Paul");
    }
} 