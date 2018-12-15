package streamStudy;

import javax.xml.bind.SchemaOutputResolver;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Demo4 {
    public static void main(String[] args) throws IOException {
        Stream<String> lines = Files.lines(Paths.get("heroes.txt"));
        List<Heroes> stu=new ArrayList<>();
        lines.forEach(s->{
            String[] str=s.split("\t");
            stu.add(new Heroes(Integer.valueOf(str[0]),str[1],str[2],str[3],Integer.valueOf(str[4]),Integer.valueOf(str[5]),Integer.valueOf(str[6])));

        });
        // 1. 找到武将中武力前三的hero对象, 提示流也可以排序
        // 2. 按出生地分组
        // 3. 找出寿命前三的武将
        // 4. 女性寿命最高的
        // 5. 找出武力排名前三  100, 99, 97 97 ==> 4个人 吕布", "张飞", "关羽", "马超
        // 6. 按各个年龄段分组： 0~20, 2140, 41~60, 60以上
        // 7. 按武力段分组： >=90， 80~89, 70~79, <70
        // 8. 按出生地分组后，统计各组人数

        //Map<Integer,List<Heroes>> map=stu.stream().collect(Collectors.groupingBy(s->s.getAbility()));

        //1:找到武将中武力前三的hero对象, 提示流也可以排序
        getAbilityTopThree(stu);
        System.out.println("--------------------------");
//        //2按出生地分组
        getHomeGroup(stu);
        System.out.println("-----------------------");
//        //3 找出寿命前三的武将
        getLifeTopThree(stu);
        System.out.println("---------------------");
//        //4:女性寿命最高的
        getFemaleLifeTop(stu);
        System.out.println("---------------------");
//        //5:找出武力排名前三  100, 99, 97 97 ==> 4个人 吕布", "张飞", "关羽", "马超
        //Map<Integer, List<Heroes>> collect = stu.stream().collect();


        System.out.println("----------------------");
//        // 6. 按各个年龄段分组： 0~20, 2140, 41~60, 60以上
        getLifeGroup(stu);
        System.out.println("--------------------------------");
        // 8. 按出生地分组后，统计各组人数
        getHomeGroupAndNumber(stu);
    }

    private static void getHomeGroupAndNumber(List<Heroes> stu) {
        Map<String,Integer> homeMap = stu.stream().collect(Collectors.groupingBy(s -> s.getHome(), Collectors.summingInt(t -> 1)));
        System.out.println(homeMap);
    }

    private static void getLifeGroup(List<Heroes> stu) {
        Map<Integer, List<Heroes>> ageGroup = stu.stream().collect(Collectors.groupingBy((s -> {

            return (s.getDead() - s.getBirth()) / 20;
        })));
        ageGroup.forEach((k,v)-> System.out.println(k+","+v));
        System.out.println("-------------------------------------");
        // 7. 按武力段分组： >=90， 80~89, 70~79, <70
        Map<Integer, List<Heroes>> map1 = stu.stream().collect(Collectors.groupingBy((s -> s.getAbility() / 10)));

        Map<String, List<Heroes>> abilityGroup=new HashMap<>();
        Set<Map.Entry<Integer, List<Heroes>>> entries = map1.entrySet();
        List<Heroes> list7=new ArrayList<>();
        List<Heroes> list8=new ArrayList<>();
        List<Heroes> list9=new ArrayList<>();
        List<Heroes> list10=new ArrayList<>();
        for(Map.Entry<Integer, List<Heroes>> ele:entries){
            for(Heroes ala:ele.getValue()){
                if(ala.getAbility()<70){
                    list7.add(ala);
                }
                else if(ala.getAbility()<=79&&ala.getAbility()>=70){
                    list8.add(ala);
                }
                else if(ala.getAbility()<=89&&ala.getAbility()>=80){
                    list9.add(ala);
                }
                else if(ala.getAbility()>=90){
                    list10.add(ala);
                }
            }
            abilityGroup.put("<70",list7);
            abilityGroup.put("70~79",list8);
            abilityGroup.put("80~89",list9);
            abilityGroup.put(">=90",list10);

        }
        abilityGroup.forEach((k,v)-> System.out.println(k+" "+v));
        System.out.println(abilityGroup);
    }

    private static void getFemaleLifeTop(List<Heroes> stu) {
        Optional<Heroes> nvMax = stu.stream().filter(s -> s.getSex().equals("女")).max((a, b) -> {
            return (a.getDead() - a.getBirth())-(b.getDead() - b.getBirth());
        });
        System.out.println(nvMax);
    }

    private static void getLifeTopThree(List<Heroes> stu) {
        List<Heroes> agelist = stu.stream().sorted((a, b) -> {
            return (b.getDead() - b.getBirth()) - (a.getDead() - a.getBirth());
        }).limit(3).collect(Collectors.toList());
        System.out.println(agelist);
    }

    private static void getHomeGroup(List<Heroes> stu) {
        Map<String, List<Heroes>> map = stu.stream().collect(Collectors.groupingBy(s -> s.getHome()));
        System.out.println(map);
    }

    private static void getAbilityTopThree(List<Heroes> stu) {
        List<Heroes> abilityList = stu.stream().sorted((a,b)->{
            return  b.getAbility()-a.getAbility() ;
        }).limit(3).collect(Collectors.toList());
        System.out.println(abilityList);
    }
}
