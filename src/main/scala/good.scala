package good

abstract class RS {
}

abstract class Char[A<:RS] {
	var name:String 
}

abstract class Com[A<:RS] {
	var participants: List[Char[A]]

	def turn():Unit
}

abstract class AbstractFactory[A<:RS] {
	def createCom(p: List[Char[A]]): Com[A]
}

abstract class CharBuilder[A<:RS] {
	def buildChar(): Char[A]
}


class G extends RS {
	var name = "G"
}

class GAF extends AbstractFactory[G] {
	def createCom(p: List[Char[G]]): Com[G] = {
		var result = new GCom
		result.participants = p
		result
	}

	def createCharBuilder(): CharBuilder[G] = {
		new GCharBuilder
	}
}

class GChar(var name: String) extends Char[G] {
	var st: Int = 10
	var dx: Int = 10
	var iq: Int = 10
	var ht: Int = 10
}

class GCharBuilder extends CharBuilder[G] {
	// private var name: String
	// private var st: Int
	// private var dx: Int
	// private var iq: Int
	// private var ht: Int	
	private var char: GChar = new GChar("Anonymous")
	def withName(arg: String) = { char.name = arg; this }
	
	def buildChar(): GChar = {
		char
	}

	def withSt(arg: Int) = { char.st = arg; this }
	def withDx(arg: Int) = { char.dx = arg; this }
	def withIq(arg: Int) = { char.iq = arg; this }
	def withHt(arg: Int) = { char.ht = arg; this }
}

class GCom extends Com[G] {
	var participants: List[Char[G]] = List()

	def turn():Unit = {
		println("GRPS brawl")
		participants.foreach(x => println(x.name))
	}
}

object app {
	def main(args: Array[String]) = {
		val GAF = new GAF
		var seq = GAF.createCom(List(new GChar("part. A"), new GChar("part. B")))
		seq.turn()	
	}
}