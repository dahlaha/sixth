import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class BlackListFilterChecker implements BlackListFilter {
    public static void main(String[] args) {
        List<String> comments=new ArrayList<>(List.of("плохой комментарий","хороший комментарий", "плохие, но",
                "хорошие слова", "очень \nплохой комментарий","УжАсНый комментарий"));
        Set<String> blackList=new HashSet<>();
        blackList.add("плохой");
        blackList.add("плохие");
        blackList.add("ужасный");
        BlackListFilter check=new BlackListFilterChecker();
        check.filterComments(comments,blackList);
        for (String s : comments) {
            System.out.println(s);
        }
    }
    /**
     * Удаляет из списка комментариев те комментарии,
     * что содержат в себе слова из черного списка.
     *
     * @param comments список комментариев; каждый комментарий
     *                 представляет собой последовательность
     *                 слов, разделенных пробелами, знаками
     *                 препинания или переносами строк
     * @param blackList список слов, которых не
     *                  должно быть в комментарии
     */
    @Override
    public void filterComments(List<String> comments, Set<String> blackList) {
        for (int i = comments.size() - 1; i >= 0; i--) {
            String comment = comments.get(i);
            String[] words = comment.split("\\p{P}?[ \\t\\n\\r]+");
            for (String word : words) {
                if (blackList.contains(word.toLowerCase())) {
                    comments.remove(i);
                    break;
                }
            }
        }
    }
}
