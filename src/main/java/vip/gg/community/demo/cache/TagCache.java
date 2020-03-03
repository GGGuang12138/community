package vip.gg.community.demo.cache;

import org.apache.commons.lang3.StringUtils;
import vip.gg.community.demo.dto.TagDTO;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Creat by GG
 * Date on 2020/3/2  10:54 下午
 */
public  class TagCache {
    public static List<TagDTO> get(){
        List<TagDTO> tagDTOS = new ArrayList<>();

        TagDTO program = new TagDTO();
        program.setCategoryName("开发语言");
        program.setTags(Arrays.asList("java","c","c++","php","perl","python","javascript","c#","ruby","go","lua","node.js",
                "erlang","scala","bash","actionscript","golang","typescript","dart","sql","objective-c","swift"));
        tagDTOS.add(program);

        TagDTO framework = new TagDTO();
        framework.setCategoryName("平台框架");
        framework.setTags(Arrays.asList("spring","laravel","django","flask","tornado","express","flutter","yii","ruby-on-rails",
                "koa","struts"));
        tagDTOS.add(framework);

        TagDTO server = new TagDTO();
        server.setCategoryName("服务器");
        server.setTags(Arrays.asList("linux","unixubuntu","windows-server","centos","负载均衡","缓存","apache","nginx"));
        tagDTOS.add(server);

        TagDTO db = new TagDTO();
        db.setCategoryName("数据库");
        db.setTags(Arrays.asList("mysql","sqlite","oracle","sql","nosql","redis","mongodb","memcached","postgresql","sql server"));
        tagDTOS.add(db);

        TagDTO front = new TagDTO();
        front.setCategoryName("前端开发");
        front.setTags(Arrays.asList("html","html5","css","css3","javascript","jquery","json","ajax","正则表达式","bootstrap",
                "javascript","jquery","node.js","angular.js","typescript","ecmascript","vue.js","react.js"
        ));
        tagDTOS.add(front);
        
        TagDTO tools = new TagDTO();
        tools.setCategoryName("开发工具");
        tools.setTags(Arrays.asList("vim","emacs","ide","eclipse","xcode","intellij-idea","textmate","sublime-text",
                "visual-studio","git","github","svn","hg","docker","ci"
        ));
        tagDTOS.add(tools);
        return tagDTOS;
    }
    public static String filterInValid(String tags){
        String[] split = StringUtils.split(tags,"/");//获得框内tag，字符串数组
        List<TagDTO> tagDTOS = get();
        //将全部标签转化成列表
        List<String> tagList = tagDTOS.stream().flatMap(tag -> tag.getTags().stream()).collect(Collectors.toList());
        //将split转为列表进行过滤，过滤出不相等的，返回字符串
        String invalid = Arrays.stream(split).filter(t -> !tagList.contains(t)).collect(Collectors.joining("/"));
        return invalid;
    }
}

