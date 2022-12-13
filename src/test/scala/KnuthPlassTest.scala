import org.scalatest._
import flatspec._
import matchers._

class KnuthPlassTest extends AnyFlatSpec with should.Matchers with PrivateMethodTester {

  def fixtures: Object {
    val loremIpsum: String

    val maxWidthOfLine: Int
  } = new {
    val loremIpsum: String = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Pellentesque sagittis elementum nunc, sed auctor ligula scelerisque sed. Pellentesque habitant morbi tristique senectus et netus et malesuada fames ac turpis egestas. Curabitur vestibulum tempus luctus. Nullam rhoncus blandit nunc, eget elementum magna accumsan et. Etiam vestibulum nec odio non porttitor. Proin id nisi varius nunc facilisis finibus. Pellentesque aliquet neque nibh, sit amet eleifend mi pretium quis. Fusce at mauris massa. Donec blandit nec nunc vel ullamcorper. Maecenas nec elit nisl. Sed id dui faucibus, luctus est a, posuere ipsum. Donec consectetur, ex ac volutpat mattis, odio risus blandit risus, sit amet viverra ipsum ante a libero. Suspendisse ex leo, euismod ac semper non, tincidunt a velit. Etiam quis rhoncus nibh, sed accumsan nisi. Curabitur libero nisi, dictum ac lacinia id, pulvinar eget dolor. Praesent aliquet libero et pretium laoreet. Pellentesque habitant morbi tristique senectus et netus et malesuada fames ac turpis egestas. Morbi tincidunt dolor augue, nec suscipit purus semper sed. Maecenas metus ex, gravida nec imperdiet eu, maximus nec quam. Aliquam mi ex, facilisis at sem sed, viverra hendrerit ex. Mauris aliquet metus eget nulla vulputate, in faucibus nisi tincidunt. Donec at arcu non lacus ultrices cursus et et."
    val maxWidthOfLine: Int = 60
  }

  it should "return a formatted text with a maximum line width of 60 on 200 characters of Lorem Ipsum" in {
    val knuthPlass = new KnuthPlass(width=fixtures.maxWidthOfLine, text=fixtures.loremIpsum)
    val formattedLoremIpsum: String = knuthPlass()

    formattedLoremIpsum should be ("Lorem ipsum dolor sit amet, consectetur adipiscing elit.\nPellentesque sagittis elementum nunc, sed auctor ligula\nscelerisque sed. Pellentesque habitant morbi tristique\nsenectus et netus et malesuada fames ac turpis egestas.\nCurabitur vestibulum tempus luctus. Nullam rhoncus blandit\nnunc, eget elementum magna accumsan et. Etiam vestibulum\nnec odio non porttitor. Proin id nisi varius nunc facilisis\nfinibus. Pellentesque aliquet neque nibh, sit amet eleifend\nmi pretium quis. Fusce at mauris massa. Donec blandit nec\nnunc vel ullamcorper. Maecenas nec elit nisl. Sed id dui\nfaucibus, luctus est a, posuere ipsum. Donec consectetur,\nex ac volutpat mattis, odio risus blandit risus, sit amet\nviverra ipsum ante a libero. Suspendisse ex leo, euismod\nac semper non, tincidunt a velit. Etiam quis rhoncus nibh,\nsed accumsan nisi. Curabitur libero nisi, dictum ac lacinia\nid, pulvinar eget dolor. Praesent aliquet libero et pretium\nlaoreet. Pellentesque habitant morbi tristique senectus et\nnetus et malesuada fames ac turpis egestas. Morbi tincidunt\ndolor augue, nec suscipit purus semper sed. Maecenas metus\nex, gravida nec imperdiet eu, maximus nec quam. Aliquam\nmi ex, facilisis at sem sed, viverra hendrerit ex. Mauris\naliquet metus eget nulla vulputate, in faucibus nisi\ntincidunt. Donec at arcu non lacus ultrices cursus et et.")
  }

  it should "return an empty string when empty text is passed" in {
    val knuthPlass = new KnuthPlass(width=fixtures.maxWidthOfLine, text="")
    val formattedText: String = knuthPlass()

    formattedText should be ("")
  }

  it should "break by word when width is zero" in {
    val knuthPlass = new KnuthPlass(width=0, text=fixtures.loremIpsum)
    val formattedText: String = knuthPlass()

    formattedText should be("Lorem\nipsum\ndolor\nsit\namet,\nconsectetur\nadipiscing\nelit.\nPellentesque\nsagittis\nelementum\nnunc,\nsed\nauctor\nligula\nscelerisque\nsed.\nPellentesque\nhabitant\nmorbi\ntristique\nsenectus\net\nnetus\net\nmalesuada\nfames\nac\nturpis\negestas.\nCurabitur\nvestibulum\ntempus\nluctus.\nNullam\nrhoncus\nblandit\nnunc,\neget\nelementum\nmagna\naccumsan\net.\nEtiam\nvestibulum\nnec\nodio\nnon\nporttitor.\nProin\nid\nnisi\nvarius\nnunc\nfacilisis\nfinibus.\nPellentesque\naliquet\nneque\nnibh,\nsit\namet\neleifend\nmi\npretium\nquis.\nFusce\nat\nmauris\nmassa.\nDonec\nblandit\nnec\nnunc\nvel\nullamcorper.\nMaecenas\nnec\nelit\nnisl.\nSed\nid\ndui\nfaucibus,\nluctus\nest\na,\nposuere\nipsum.\nDonec\nconsectetur,\nex\nac\nvolutpat\nmattis,\nodio\nrisus\nblandit\nrisus,\nsit\namet\nviverra\nipsum\nante\na\nlibero.\nSuspendisse\nex\nleo,\neuismod\nac\nsemper\nnon,\ntincidunt\na\nvelit.\nEtiam\nquis\nrhoncus\nnibh,\nsed\naccumsan\nnisi.\nCurabitur\nlibero\nnisi,\ndictum\nac\nlacinia\nid,\npulvinar\neget\ndolor.\nPraesent\naliquet\nlibero\net\npretium\nlaoreet.\nPellentesque\nhabitant\nmorbi\ntristique\nsenectus\net\nnetus\net\nmalesuada\nfames\nac\nturpis\negestas.\nMorbi\ntincidunt\ndolor\naugue,\nnec\nsuscipit\npurus\nsemper\nsed.\nMaecenas\nmetus\nex,\ngravida\nnec\nimperdiet\neu,\nmaximus\nnec\nquam.\nAliquam\nmi\nex,\nfacilisis\nat\nsem\nsed,\nviverra\nhendrerit\nex.\nMauris\naliquet\nmetus\neget\nnulla\nvulputate,\nin\nfaucibus\nnisi\ntincidunt.\nDonec\nat\narcu\nnon\nlacus\nultrices\ncursus\net\net.")
  }

  it should "break by word when width is negative" in {
    val knuthPlass = new KnuthPlass(width = -25, text = fixtures.loremIpsum)
    val formattedText: String = knuthPlass()

    formattedText should be("Lorem\nipsum\ndolor\nsit\namet,\nconsectetur\nadipiscing\nelit.\nPellentesque\nsagittis\nelementum\nnunc,\nsed\nauctor\nligula\nscelerisque\nsed.\nPellentesque\nhabitant\nmorbi\ntristique\nsenectus\net\nnetus\net\nmalesuada\nfames\nac\nturpis\negestas.\nCurabitur\nvestibulum\ntempus\nluctus.\nNullam\nrhoncus\nblandit\nnunc,\neget\nelementum\nmagna\naccumsan\net.\nEtiam\nvestibulum\nnec\nodio\nnon\nporttitor.\nProin\nid\nnisi\nvarius\nnunc\nfacilisis\nfinibus.\nPellentesque\naliquet\nneque\nnibh,\nsit\namet\neleifend\nmi\npretium\nquis.\nFusce\nat\nmauris\nmassa.\nDonec\nblandit\nnec\nnunc\nvel\nullamcorper.\nMaecenas\nnec\nelit\nnisl.\nSed\nid\ndui\nfaucibus,\nluctus\nest\na,\nposuere\nipsum.\nDonec\nconsectetur,\nex\nac\nvolutpat\nmattis,\nodio\nrisus\nblandit\nrisus,\nsit\namet\nviverra\nipsum\nante\na\nlibero.\nSuspendisse\nex\nleo,\neuismod\nac\nsemper\nnon,\ntincidunt\na\nvelit.\nEtiam\nquis\nrhoncus\nnibh,\nsed\naccumsan\nnisi.\nCurabitur\nlibero\nnisi,\ndictum\nac\nlacinia\nid,\npulvinar\neget\ndolor.\nPraesent\naliquet\nlibero\net\npretium\nlaoreet.\nPellentesque\nhabitant\nmorbi\ntristique\nsenectus\net\nnetus\net\nmalesuada\nfames\nac\nturpis\negestas.\nMorbi\ntincidunt\ndolor\naugue,\nnec\nsuscipit\npurus\nsemper\nsed.\nMaecenas\nmetus\nex,\ngravida\nnec\nimperdiet\neu,\nmaximus\nnec\nquam.\nAliquam\nmi\nex,\nfacilisis\nat\nsem\nsed,\nviverra\nhendrerit\nex.\nMauris\naliquet\nmetus\neget\nnulla\nvulputate,\nin\nfaucibus\nnisi\ntincidunt.\nDonec\nat\narcu\nnon\nlacus\nultrices\ncursus\net\net.")
  }

  it should "smawk should modify minima and breaks property of class" in {
    val knuthPlass = new KnuthPlass(width=fixtures.maxWidthOfLine, text=fixtures.loremIpsum)
    val smawk = PrivateMethod[KnuthPlass]('smawk)
    val getMinima = PrivateMethod[KnuthPlass]('getMinima)

    var expectedMinima: Array[Double] = Array(0.0) ++ Array.fill(200)(Math.pow(10, 20))
    expectedMinima(1) = 3025.0 //hardcoded result of smawk

    knuthPlass invokePrivate smawk(List(0), List(1))
    knuthPlass invokePrivate getMinima() should be (expectedMinima)
  }


}
