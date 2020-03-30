; https://dzone.com/articles/lambda-calculus-in-clojure-part-1


(ns lambda)
(use 'clojure.test)


(defmacro λ
  [args & body]
  `(fn [~args] ~@body))


; Identity
(def id (λ x x))
(println (id 5))

; Zero
(def zero (λ f (λ x x)))

; Successor
(def succ (λ n (λ f (λ x f (n f x)))))

; Numbers
(println zero)
(println (succ zero))
(println (succ (succ zero)))

; True
(def T
  (λ a (λ b a)))

; False
(def F
  (λ a (λ b b)))

; And
(def And
  (λ p (λ q ((p q) p))))

; Or
(def Or
  (λ p (λ q ((p p) q))))

; Not
(def Not
  (λ p ((p F) T)))

; Xor
(def Xor
  (λ a (λ b ((a (Not b)) b))))

; If
(def If
  (λ p (λ a (λ b ((p a) b)))))

(def toBoolean
  (λ f ((f true) false)))


; true AND true
(toBoolean ((And T) T))

; true AND false
(toBoolean ((And T) F))


(deftest λ-booleans

  (testing "Boolean expressions"

    ;T AND T AND F OR T

    (is (= (toBoolean ((And T) ((And T) ((Or F) T))))

             true))

    ;T AND F AND F OR T AND T

    (is (= (toBoolean ((And T) ((And F) ((Or F) ((And T) T)))))

             false))

    ;T OR (F AND F OR T AND T)

    (is (= (toBoolean ((Or T) ((And F) ((Or F) ((And T) T)))))

             true))

    ;F OR (F AND F OR F AND T)

    (is (= (toBoolean ((Or F) ((And F) ((Or F) ((And F) T)))))

             false))

    ;F OR F AND F OR T AND T

    (is (= (toBoolean ((And ((Or F) F)) ((And T) ((Or F) T))))

             false))

    ;T OR F AND F OR T AND T

    (is (= (toBoolean ((And ((Or T) F)) ((And T) ((Or F) T))))

             true))))


(def zero
  (λ f (λ x x)))

(def one
  (λ f (λ x (f x))))

(def two
  (λ f (λ x (f (f x)))))

(def succ
  (λ n (λ f (λ x (f ((n f) x))))))

(def pred
  (λ n (λ f (λ x (((n (λ g (λ h (h (g f))))) (λ u x)) (λ u u))))))

(def plus
  (λ m (λ n ((n succ) m))))

(def minus
  (λ m (λ n ((n pred) m))))

(def mult
  (λ m (λ n (λ f (m (n f))))))

(def exp
  (λ m (λ n (n m))))

(def fromInt
  (λ n
    (if (= n 0)
      zero
      (succ (fromInt (- n 1))))))

(def toInt
  (λ f ((f (λ n (+ n 1))) 0)))

(def λToStr
  (λ f ((f (λ n (format "f(%s)" n))) "n")))

(def toStr
  (λ f (format "λf.λn.(%s)" (λToStr f))))