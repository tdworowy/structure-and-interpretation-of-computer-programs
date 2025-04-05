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
        (< (weight x) (weight (first set))) (cons x set)
        :else (cons (first set) (adjoin-set x (rest set)))))

(defn make-leaf-set [pairs]
  (if (empty? pairs) ()
      (let [pair (first pairs)]
        (adjoin-set (make-leaf (first pair) (second pair))
                    (make-leaf-set (rest pairs))))))

(def sample-tree
  (make-code-tree (make-leaf 'A 4) (make-code-tree (make-leaf 'B 2) (make-code-tree (make-leaf 'D 1) (make-leaf 'C 2)))))
(def massage '(0 1 1 0 0 1 0 1 0 1 1 1 0))

(defn has-symbol? [symbol symbol-list]
  (not (nil? (some #(= % symbol) symbol-list))))

(defn encode-symbol [symbol tree]
  (if (has-symbol? symbol (symbols tree))
    (let [left (left-branch tree) right (right-branch tree)]
      (if (has-symbol? symbol (symbols left))
        (if (leaf? left)
          '(0)
          (cons 0 (encode-symbol symbol left)))
        (if (leaf? right)
          '(1)
          (cons 1 (encode-symbol symbol right)))))
    (println "Symbol not in tree")))

(defn encode [message tree]
  (if (empty? message) '()
      (cons (encode-symbol (first message) tree) (encode (rest message) tree))))

(defn successive-merge [tree]
  (cond (empty? tree) nil
        (empty? (rest tree)) (first tree)
        :else (let [left (first tree)
                    right (second tree)
                    remaining (drop 2 tree)]
                (successive-merge (adjoin-set (make-code-tree left right) remaining)))))

(defn generate-huffman-tree [pairs]
  (successive-merge (make-leaf-set pairs)))

(println (decode massage sample-tree))
(println (first '(A  D  A  B  B  B  A)))
(println (symbols sample-tree))
(println (has-symbol? 'A '(A  D  A B B B A)))
(println (encode '(A D A B B C A) sample-tree))

(def sample-tree1
  (generate-huffman-tree
   '((A 2) (B 3))))
(println sample-tree1)

(println (make-leaf-set '((A 2) (B 3))))
(println (make-leaf-set '((AA 2) (BB 3))))

(def sample-tree2
  (generate-huffman-tree
   '((A 2) (NA 16) (BOOM 1) (SHA 3) (GET 2)
           (YIP 9) (JOB 2) (WAH 1))))

(println sample-tree2)
(println (encode '(GET A JOB SHA NA NA NA NA NA NA NA GET A JOB SHA NA NA NA NA NA NA NA WAH YIP YIP YIP YIP YIP YIP YIP YIP YIP YIP SHA BOOM) sample-tree2))
(println (decode '(1 1 1 1 1 1 1 0 0 1 1 1 1 0 1 1 1 0 0 0 0 0 0 0 0 1 1 1 1 1 1 1 0 0 1 1 1 1 0 1 1 1 0 0 0 0 0 0 0 0 1 1 0 1 0 1 0 1 0 1 0 1 0 1 0 1 0 1 0 1 0 1 0 1 0 1 1 1 0 1 1 0 1 1) sample-tree2))
(println (= 'A (first '(A D A B B B A))))
(println (= 'A 'A))

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

