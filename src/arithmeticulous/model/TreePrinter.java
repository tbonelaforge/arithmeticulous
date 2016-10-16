package arithmeticulous.model;

import arithmeticulous.model.PrintableTree;

public class TreePrinter {
    public static void printAsHTML(PrintableTree node) {
        String htmlString = getHTML(node);
        System.out.println(htmlString);
    }

   private static String template = "<table border=\"1\"><tr><td colspan=\"2\" style=\"text-align:center;\">%s</td></tr><tr>%s</tr></table>";

    private static String getHTML(PrintableTree node) {
        String thisData = node.getDataAsHTML();
        String childHTML = getChildrenAsTableCells(node);
        String htmlString = String.format(template, thisData, childHTML);
        return htmlString;
    }

    private static String getChildrenAsTableCells(PrintableTree node) {
        String template = "<td>%s</td><td>%s</td>";
        String leftChildString = "";
        if (node.getLeftChild() != null) {
            leftChildString = getHTML(node.getLeftChild());
        }
        String rightChildString = "";
        if (node.getRightChild() != null) {
            rightChildString = getHTML(node.getRightChild());
        }
        String childrenAsHTML = String.format(template, leftChildString, rightChildString);
        return childrenAsHTML;
    }
}
