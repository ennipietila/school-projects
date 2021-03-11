package tests
import org.scalatest._
import scala.collection.mutable.{Set,Map}

import avl._

class AVLSpec extends FlatSpec with Matchers {
  val rand = new scala.util.Random(2105)

  /* A helper for getting all the (key,value) pairs in the treemap */
  def treeToSet[Key,Value](tree: TreeMap[Key,Value]): Set[(Key,Value)] = {
    val result = Set[(Key,Value)]()
    def inner(node: Node[Key,Value]): Unit = {
      if(node == null) return
      result += ((node.key,node.value))
      inner(node.left)
      inner(node.right)
    }
    inner(tree.root)
    result
  }

  def randIntStringArray(n: Int, maxValue: Int): Array[(Int,String)] = {
    require(n >= 0)
    Array.tabulate[(Int,String)](n)(i => (rand.nextInt(maxValue), "v"+i))
  }

  "The TreeMap class" should "implement insert correctly" in {
    val nofTests = 100
    val nofPairs = 15
    val maxKeyValue = 20
    for(test <- 1 to nofTests) {
      val pairs = randIntStringArray(nofPairs, maxKeyValue)
      // A reference for recording the keys in the tree: a mutable set
      val refMap = Map[Int,String]()
      // Start from the empty tree and insert keys one-by-one
      val tree = new TreeMap[Int,String]()
      for(i <- 0 until pairs.length) {
        val (key,value) = pairs(i)
        // Insert the (key,value) pair
        val oldValue = tree.insert(key, value)
        withClue("After inserting "+pairs.take(i+1).mkString(",")+", the tree is:\n"+tree.prettyString+":") {
          // The return value should be correct
          oldValue should be (refMap.get(key))
          refMap(key) = value
          // The size of the tree should be correct
          tree.size should be (refMap.size)
          // The keys in the tree should be correct
          treeToSet(tree) should be (refMap.toSet)
          // The BST property should hold in the tree
          tree.isValidBST should be (true)
          // The AVL property should hold in the tree
          tree.hasAVLProperty should be (true)
        }
      }
    }
  }


  it should "implement remove correctly" in {
    val nofTests = 100
    val nofPairs = 15
    val maxKeyValue = 20
    for(test <- 1 to nofTests) {
      val pairs = randIntStringArray(nofPairs, maxKeyValue)
      // A reference for recording the keys in the tree: a mutable set
      val refMap = Map[Int,String]()
      // Start from the empty tree and insert keys one-by-one
      val tree = new TreeMap[Int,String]()
      for(i <- 0 until pairs.length) {
        val (key,value) = pairs(i)
        // Insert the (key,value) pair
        val oldValue = tree.insert(key, value)
        withClue("After inserting "+pairs.take(i+1).mkString(",")+", the tree is:\n"+tree.prettyString+":") {
          // The return value should be correct
          oldValue should be (refMap.get(key))
          refMap(key) = value
          // The size of the tree should be correct
          tree.size should be (refMap.size)
          // The keys in the tree should be correct
          treeToSet(tree) should be (refMap.toSet)
          // The BST property should hold in the tree
          tree.isValidBST should be (true)
          // The AVL property should hold in the tree
          tree.hasAVLProperty should be (true)
        }
      }
      // Remove the keys one-by-one,
      // the tree should be consistent after each remove
      val removes = rand.shuffle(refMap.keys.toSeq)
      for(i <- 0 until removes.length) {
        val key = removes(i)
        assert(refMap.get(key) != None)
        // Remove the key
        val oldValue = tree.remove(key)
        oldValue should be (refMap.get(key))
        refMap.remove(key)
        withClue("After inserting "+pairs.mkString(",")+" and removing the keys "+removes.take(i+1).mkString(",")+", the tree is:\n"+tree.prettyString+":") {
          // The size of the tree should be correct
          tree.size should be (refMap.size)
          // The keys in the tree should be correct
          treeToSet(tree) should be (refMap.toSet)
          // The BST property should hold in the tree
          tree.isValidBST should be (true)
          // The AVL property should hold in the tree
          tree.hasAVLProperty should be (true)
        }
      }
    }
  }
}
