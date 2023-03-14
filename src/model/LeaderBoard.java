package model;
public class LeaderBoard{
    private Node root;

    public void add(Node node) {
        if (root == null) {

        } else {
            insert(node, root);

        }
    }

    public void insert(Node node, Node current) {
        if (node.getPlayer().getScore() < current.getPlayer().getScore()) {
            if (current.getLeft() == null) {
                current.setLeft(node);

            } else {
                insert(current.getLeft(), current);
            }

        } else if (node.getPlayer().getScore() > current.getPlayer().getScore()) {
            if (current.getRight() == null) {
                current.setRight(node);
                return;
            } else {
                insert(current.getRight(), node);
            }
        } else {
            System.out.println("the node already exist");
        }

    }


    /**
     * @return Node return the root
     */
    public Node getRoot() {
        return root;
    }

    /**
     * @param root the root to set
     */
    public void setRoot(Node root) {
        this.root = root;
    }

}