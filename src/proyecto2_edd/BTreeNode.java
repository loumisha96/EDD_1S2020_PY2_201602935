/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto2_edd;

/**
 *
 * @author lourd
 */
public class BTreeNode {
            int order;
/*     */   BTreeComparable[] key;
/*     */   BTreeNode[] child;
/*     */   BTreeNode parent;
/*     */   int filled;
/*     */   boolean isLeaf;
    BTreeNode(int paramInt, boolean paramBoolean)
/*     */   {
/*  33 */     this.order = paramInt;
/*  34 */     int i = paramInt << 1;
/*  35 */     this.key = new BTreeComparable[i];
              this.child = new BTreeNode[i | 0x1];
/*  37 */     this.parent = null;
/*  38 */     this.filled = 0;
/*  39 */     this.isLeaf = paramBoolean;

/*     */   }
    BTreeNode(int paramInt, BTreeNode paramBTreeNode, BTreeComparable[] paramArrayOfBTreeComparable, BTreeNode[] paramArrayOfBTreeNode, boolean paramBoolean) {
/*  44 */     this(paramInt, paramBoolean);
/*     */ 
/*  46 */     if ((paramArrayOfBTreeComparable.length > paramInt << 1) || (paramArrayOfBTreeComparable.length != paramArrayOfBTreeNode.length - 1)) {
/*  47 */       throw new IllegalArgumentException("order: " + paramInt + "objects.length: " + paramArrayOfBTreeComparable.length + "theirSons.length: " + paramArrayOfBTreeNode.length);
/*     */     }
/*  49 */     this.parent = paramBTreeNode;
/*  50 */     this.filled = paramArrayOfBTreeComparable.length;
/*     */ 
/*  52 */     System.arraycopy(paramArrayOfBTreeComparable, 0, this.key, 0, this.filled);
/*  53 */     System.arraycopy(paramArrayOfBTreeNode, 0, this.child, 0, this.filled + 1);
/*     */   }
    public int findKeyPosition(BTreeComparable paramBTreeComparable)
/*     */   {
            int j;
/*  69 */     if (isEmpty())
/*  70 */       throw new IllegalStateException();
/*     */     int i;
/*  74 */     for (j = i = 0; i < this.filled; i++) {
/*  75 */       int k = paramBTreeComparable.compareTo(this.key[i]);
/*  76 */       if (k < 0) {
/*  77 */         return j;
/*     */       }
/*  79 */       j++;
/*     */ 
/*  81 */       if (k == 0) {
/*  82 */         return j;
/*     */       }
/*  84 */       j++;
/*     */     }
/*  86 */     return j;
/*     */   }
/*     */ 
/*     */   public int findChildPosition(BTreeNode paramBTreeNode) {
/*  90 */     if (isEmpty())
/*  91 */       throw new IllegalStateException("nodo vacio");
/*  92 */     for (int i = 0; i <= this.filled; i++)
/*  93 */       if (this.child[i] == paramBTreeNode)
/*  94 */         return i;
/*  95 */     throw new IllegalStateException("no se encuentra en nodo");
/*     */   }
/*     */ 
/*     */   public String toString() {
/*  99 */     String str = "<[";
/*     */ 
/* 101 */     for (int i = 0; i <= this.order << 1; i++) {
/* 102 */       if (this.child[i] != null)
/* 103 */         str = str + "{" + this.child[i].toString() + "}";
/* 104 */       if (i < this.filled) {
/* 105 */         str = str + " (" + this.key[i].toString() + ") ";
/*     */       }
/* 107 */       else if (i < this.order << 1) {
/* 108 */         str = str + " . ";
/*     */       }
/*     */     }
/* 111 */     return str + "]>";
/*     */   }
/*     */ 
/*     */   public void cleanNode() {
/* 115 */     int i = this.order << 1;
/* 116 */     for (int j = this.filled; j < i; j++) {
/* 117 */       this.key[j] = null;
/* 118 */       this.child[(j + 1)] = null;
/*     */     }
/*     */   }
/*     */ 
/*     */   public int getOrder()
/*     */   {
/* 125 */     return this.order;
/*     */   }
/*     */ 
/*     */   public int getFilled() {
/* 129 */     return this.filled;
/*     */   }
/*     */ 
/*     */   public BTreeNode getParent() {
/* 133 */     return this.parent;
/*     */   }
/*     */ 
/*     */   public BTreeNode getChild(int paramInt) {
/* 137 */     if ((paramInt < 0) || (paramInt > this.filled) || (isEmpty())) {
/* 138 */       throw new IndexOutOfBoundsException("filled: " + this.filled + " position: " + paramInt);
/*     */     }
/* 140 */     return this.child[paramInt];
/*     */   }
/*     */ 
/*     */   public BTreeComparable getKey(int paramInt) {
/* 144 */     if ((paramInt < 0) || (paramInt >= this.filled) || (isEmpty())) {
/* 145 */       throw new IndexOutOfBoundsException("filled: " + this.filled + " position: " + paramInt);
/*     */     }
/* 147 */     return this.key[paramInt];
/*     */   }
/*     */ 
/*     */   public boolean isLeaf() {
/* 151 */     return this.isLeaf;
/*     */   }
/*     */ 
/*     */   public boolean isEmpty() {
/* 155 */     return getFilled() == 0;
/*     */   }
int findKeyPosition(int i) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
   
}
