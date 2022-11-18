package ru.job4j.tree;

import java.util.LinkedList;
import java.util.Optional;
import java.util.Queue;
import java.util.function.Predicate;

public class SimpleTree<E> implements Tree<E> {
    private final Node<E> root;

    public SimpleTree(final  E root) {
        this.root = new Node<>(root);
    }

    @Override
    public boolean add(E parent, E child) {
        boolean result = false;
        var nodeRoot = findBy(parent);
        var nodeChild = findBy(child);
        return (nodeRoot.isPresent() && nodeChild.isEmpty())
                ? root.children.add(new Node<>(child)) : result;
    }

    @Override
    public Optional<Node<E>> findBy(E value) {
        Predicate<Node<E>> findBy = eNode -> eNode.value.equals(value);
        return findByPredicate(findBy);
    }
    @Override
    public boolean isBinary() {
        Predicate<Node<E>> isBinary = eNode -> eNode.children.size() > 2;
        return findByPredicate(isBinary).isEmpty();
    }

    private Optional<Node<E>> findByPredicate(Predicate<Node<E>> condition) {
        Optional<Node<E>> result = Optional.empty();
        Queue<Node<E>> data = new LinkedList<>();
        data.offer(this.root);
        while (!data.isEmpty()) {
            Node<E> el = data.poll();
            if (condition.test(el)) {
                result = Optional.of(el);
                break;
            }
            data.addAll(el.children);
        }
        return result;
    }
}
