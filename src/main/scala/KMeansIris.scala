import org.apache.spark.SparkContext
import org.apache.spark.mllib.clustering.KMeans
import org.apache.spark.mllib.linalg.Vectors

object KMeansIris extends App {
  println("## start")

  val context = new SparkContext("local", "demo")

  val data = context.
  textFile("iris.data").
  map { s =>
    val elems = s.split(",")
    
    println(elems.last, elems.init.map(_.toDouble).getClass(), Vectors.dense(elems.init.map(_.toDouble)))
    (elems.last, Vectors.dense(elems.init.map(_.toDouble)))
  }

  val k = 3 // クラスタの個数を指定します
  val maxItreations = 100 // K-means のイテレーション最大回数を指定します
  val clusters = KMeans.train(data.map(_._2), k, maxItreations)

  // 各クラスタの中心を確認する
  println("## クラスタの中心")
  clusters.clusterCenters.foreach {
    center => println(f"${center.toArray.mkString("[", ", ", "]")}%s")
  }

  // 各データがどのクラスタに分類されたのかを確認する
  println("## 各データのクラスタリング結果")
  data.foreach { tuple =>
    println(f"${tuple._2.toArray.mkString("[", ", ", "]")}%s " +
            f"(${tuple._1}%s) : cluster = ${clusters.predict(tuple._2)}%d")
  }
}
