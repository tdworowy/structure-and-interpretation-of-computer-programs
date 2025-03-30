(defn make-leaf [symbol weight]
  (list 'leaf symbol weight))

(defn leaf? [object]
  (= (first object) 'leaf))

(defn symbol-leaf [x]
  (second x))

(defn weight-leaf [x]
  (nth x 2))

(defn symbols [tree]
  (if (leaf? tree)
    (list (symbol-leaf tree))
    (nth tree 2)))

(defn weight [tree]
  (if (leaf? tree) (weight-leaf tree)
      (nth tree 3)))

(defn make-code-tree [left right]
  (list left right (concat (symbols left) (symbols right))
        (+ (weight left) (weight right))))

(defn left-branch [tree] (first tree))
(defn right-branch [tree] (second tree))

(defn choose-branch [bit branch]
  (cond (= bit 0) (left-branch branch)
        (= bit 1) (right-branch branch)
        :else (println "ERROR bad bit -- choose-branch" bit)))

(defn decode [bits tree]
  (letfn [(decode-1 [bits current-branch]
            (if (empty? bits) '()
                (let [next-branch (choose-branch (first bits) current-branch)]
                  (if (leaf? next-branch)
                    (cons (symbol-leaf next-branch) (decode-1 (rest bits) tree))
                    (decode-1 (rest bits) next-branch)))))] (decode-1 bits tree)))

(defn adjoin-set [x set]
  (cond (empty? set) (list x)
        (< (weight x) (weight (first set)) (cons x set))
        :else (cons (first set) (adjoin-set x (rest set)))))

(defn make-leaf-set [pairs]
  (if (empty? pairs) '()
      (let [pair (first pairs)]
        (adjoin-set (make-leaf (first pair) (second pair))
                    (make-leaf-set (rest pairs))))))

(def leaf-a (make-leaf 'A 4))
(def leaf-b (make-leaf 'B 2))
(def leaf-c (make-leaf 'C 1))

(def subtree (make-code-tree leaf-b leaf-c))
(println subtree)
(def huffman-tree (make-code-tree leaf-a subtree))
(def bits '(0 1 0 1 1 0))
(def decoded-message (decode bits huffman-tree))

(println "Huffman Tree:" huffman-tree)
(println "Bits to decode:" bits)
(println "Decoded message:" decoded-message)