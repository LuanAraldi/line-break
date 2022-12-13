# Line Break
This code was developed based on the famous Knuth Plass algorithm for line breaks. It focus on making sure that the text
should not flutuate too much on size depending on the maximum width passed to the function, this occurs because
the text might look a little fuzzy and jarring depending on how much fluctuation exists between lines. This implemented
version also uses a modified version of the SMAWK algorithm to calculate the minima values of the line cost matrix, this 
can only be possible when using a totally monotone matrix (which I am), and it should drop the time complexity of the whole
algorithm to linear time of O(n)

Usage:
```Scala
    // It only needs the maximum width of the line and the text
    val knuthPlass = new KnuthPlass(width=60, text="text here")
    
    knuthPlass() // This will return the text formatted as a String
```