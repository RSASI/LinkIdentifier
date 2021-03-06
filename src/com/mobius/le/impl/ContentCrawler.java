/*     */ package com.mobius.le.impl;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ContentCrawler
/*     */ {
/*     */   public static String CleanRegex(String content)
/*     */   {
/*  24 */     String normalizedContent = content;
/*     */     
/*  26 */     normalizedContent = normalizedContent.replaceAll("\\.", "\\\\.");
/*  27 */     normalizedContent = normalizedContent.replaceAll("\\^", "\\\\^");
/*  28 */     normalizedContent = normalizedContent.replaceAll("\\$", "\\\\$");
/*  29 */     normalizedContent = normalizedContent.replaceAll("\\|", "\\\\|");
/*  30 */     normalizedContent = normalizedContent.replaceAll("\\(", "\\\\(");
/*  31 */     normalizedContent = normalizedContent.replaceAll("\\)", "\\\\)");
/*  32 */     normalizedContent = normalizedContent.replaceAll("\\[", "\\\\[");
/*  33 */     normalizedContent = normalizedContent.replaceAll("\\]", "\\\\]");
/*  34 */     normalizedContent = normalizedContent.replaceAll("\\{", "\\\\{");
/*  35 */     normalizedContent = normalizedContent.replaceAll("\\}", "\\\\}");
/*  36 */     normalizedContent = normalizedContent.replaceAll("\\*", "\\\\*");
/*  37 */     normalizedContent = normalizedContent.replaceAll("\\+", "\\\\+");
/*  38 */     normalizedContent = normalizedContent.replaceAll("\\?", "\\\\?");
/*  39 */     normalizedContent = normalizedContent.replaceAll("\\\\", "\\\\");
/*  40 */     return normalizedContent;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static String contentNormalization(String content)
/*     */   {
/*  50 */     String normalizedContent = content;
/*     */     
/*  52 */     normalizedContent = normalizedContent.replaceAll("(?si)<script[^<>]*?>.*?</script>", "");
/*  53 */     normalizedContent = normalizedContent.replaceAll("(?si)<iframe[^<>]*?>.*?</iframe>", "");
/*  54 */     normalizedContent = normalizedContent.replaceAll("(?si)<iframe[^<>]*?>", "");
/*     */     
/*  56 */     normalizedContent = normalizedContent.replaceAll("&#65533;", "'");
/*  57 */     normalizedContent = normalizedContent.replaceAll("(?si)<img[^<>]*?>|</img>", "");
/*  58 */     normalizedContent = normalizedContent.replaceAll("(?si)<style[^<>]*?>.*?</style>", "");
/*  59 */     normalizedContent = normalizedContent.replaceAll("(?si)<noscript[^<>]*?>.*?</noscript>", "");
/*  60 */     normalizedContent = normalizedContent.replaceAll("<noscript/>", "");
/*  61 */     normalizedContent = normalizedContent.replaceAll("display\\s*:\\s*none\\s*", "");
/*  62 */     normalizedContent = normalizedContent.replaceAll("(?sim)<head>.*?</head>", "");
/*  63 */     normalizedContent = normalizedContent.replaceAll("(?sim)<!--[^<>]*>", "");
/*  64 */     normalizedContent = normalizedContent.replaceAll("(?sim)<!--.*?-->", "");
/*  65 */     normalizedContent = normalizedContent.replaceAll("(?sim)\\s\\s+", "\\\n");
/*  66 */     normalizedContent = normalizedContent.replaceAll("(?sim)&nbsp;", " ");
/*  67 */     normalizedContent = normalizedContent.replaceAll("(?sim)&amp;", "&");
/*  68 */     normalizedContent = normalizedContent.replaceAll("(?sim)<.*?>", " ");
/*  69 */     return normalizedContent;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static String CleanContent(String strClean)
/*     */   {
/*  80 */     strClean = strClean.replaceAll("(?sim)(?sim)<[^<>]+>", "");
/*  81 */     strClean = strClean.replaceAll("(?sim)&reg;", "");
/*  82 */     strClean = strClean.replaceAll("(?sim)&nbsp;", " ");
/*  83 */     strClean = strClean.replaceAll("(?sim)\\s+", " ");
/*  84 */     strClean = strClean.replaceAll("(?sim)&amp", "&");
/*  85 */     strClean = strClean.replaceAll("(?sim)&#9658;", "");
/*  86 */     strClean = strClean.replaceAll("(?sim)??;", "");
/*  87 */     strClean = strClean.replaceAll("(?sim)&#9658;", "");
/*  88 */     strClean = strClean.replaceAll("(?sim)???", "");
/*  89 */     strClean = strClean.replaceAll("(?sim)???", "");
/*  90 */     strClean = strClean.replaceAll("(?sim)&#x201c", " ");
/*  91 */     strClean = strClean.replaceAll("(?sim)&#x201d", "");
/*  92 */     strClean = strClean.replaceAll("(?sim)&quot;", "");
/*  93 */     strClean = strClean.replaceAll("(?sim);", "");
/*  94 */     strClean = strClean.replaceAll("(?sim)\"", "");
/*  95 */     strClean = strClean.replaceAll("(?sim)???", "");
/*  96 */     strClean = strClean.replaceAll("(?sim)\\[\\]", "");
/*  97 */     strClean = strClean.replaceAll("(?sim)&nbsp;", " ");
/*  98 */     strClean = strClean.replaceAll("(?sim)\\s+", " ");
/*  99 */     strClean = strClean.replaceAll("(?sim)&#9658;", "");
/* 100 */     strClean = strClean.replaceAll("(?sim)&#x201d|????;|?????|????|&#9658;|&quot;|?????|?????", "");
/* 101 */     strClean = strClean.replaceAll("(?sim)&#x201c", " ");
/* 102 */     strClean = strClean.replaceAll("(?sim)?? ", "\\S");
/* 103 */     strClean = strClean.replaceAll("(?sim)\"\"", "\"");
/* 104 */     strClean = strClean.replaceAll("(?sim)&#039;", "'");
/* 105 */     strClean = strClean.replaceAll("(?sim)&#233", "e");
/* 106 */     strClean = strClean.replaceAll("(?sim)&#235", "e");
/* 107 */     strClean = strClean.replaceAll("(?sim)&#8211;", "-");
/* 108 */     strClean = strClean.replaceAll("(?sim)&#8213;", "-");
/* 109 */     strClean = strClean.replaceAll("(?sim)&#8217;", "'");
/* 110 */     strClean = strClean.replaceAll("(?sim)&#8220;", "\"");
/* 111 */     strClean = strClean.replaceAll("(?sim)&#8221;", "\"");
/* 112 */     strClean = strClean.replaceAll("(?sim)&amp;", "&");
/* 113 */     strClean = strClean.replaceAll("(?sim)&bull;", "6");
/* 114 */     strClean = strClean.replaceAll("(?sim)&euro;", "???????");
/* 115 */     strClean = strClean.replaceAll("(?sim)&euro;-", "???????");
/* 116 */     strClean = strClean.replaceAll("(?sim)&ldquo;", "\"");
/* 117 */     strClean = strClean.replaceAll("(?sim)&mdash;", "'");
/* 118 */     strClean = strClean.replaceAll("(?sim)&nbsp;", "\\S");
/* 119 */     strClean = strClean.replaceAll("(?sim)&ndash;", "-");
/* 120 */     strClean = strClean.replaceAll("(?sim)&rdquo;", "????? ");
/* 121 */     strClean = strClean.replaceAll("(?sim)&rsquo;", "'");
/* 122 */     strClean = strClean.replaceAll("(?sim)&rsquo;", "");
/* 123 */     strClean = strClean.replaceAll("(?sim)&rsquo;-", "'");
/* 124 */     strClean = strClean.replaceAll("(?sim)????", "\"");
/* 125 */     strClean = strClean.replaceAll("(?sim)????", "???????");
/* 126 */     strClean = strClean.replaceAll("(?sim)??????????????", "'");
/* 127 */     strClean = strClean.replaceAll("(?sim)????", "a");
/* 128 */     strClean = strClean.replaceAll("(?sim)?? ", "A");
/* 129 */     strClean = strClean.replaceAll("(?sim)?? ", "a");
/* 130 */     strClean = strClean.replaceAll("(?sim)?????", "A");
/* 131 */     strClean = strClean.replaceAll("(?sim)?????", "");
/* 132 */     strClean = strClean.replaceAll("(?sim)????", "a");
/* 133 */     strClean = strClean.replaceAll("(?sim)????", "a");
/* 134 */     strClean = strClean.replaceAll("(?sim)?????", "a");
/* 135 */     strClean = strClean.replaceAll("(?sim)????", "a");
/* 136 */     strClean = strClean.replaceAll("(?sim)????", "a");
/* 137 */     strClean = strClean.replaceAll("(?sim)????", "a");
/* 138 */     strClean = strClean.replaceAll("(?sim)?????", "a");
/* 139 */     strClean = strClean.replaceAll("(?sim)????", "a");
/* 140 */     strClean = strClean.replaceAll("(?sim)????????", "e");
/* 141 */     strClean = strClean.replaceAll("(?sim)????????", "o");
/* 142 */     strClean = strClean.replaceAll("(?sim)???????????????", "????");
/* 143 */     strClean = strClean.replaceAll("(?sim)???????????????", "???????");
/* 144 */     strClean = strClean.replaceAll("(?sim)a????", "n");
/* 145 */     strClean = strClean.replaceAll("(?sim)????????", "n");
/* 146 */     strClean = strClean.replaceAll("(?sim)????????", "n");
/* 147 */     strClean = strClean.replaceAll("(?sim)????????", "e");
/* 148 */     strClean = strClean.replaceAll("(?sim)a????", "a");
/* 149 */     strClean = strClean.replaceAll("(?sim)?????????", "????");
/* 150 */     strClean = strClean.replaceAll("(?sim)????????", "a");
/* 151 */     strClean = strClean.replaceAll("(?sim)????????", "a");
/* 152 */     strClean = strClean.replaceAll("(?sim)a????", "c");
/* 153 */     strClean = strClean.replaceAll("(?sim)????????o", "c");
/* 154 */     strClean = strClean.replaceAll("(?sim)????????", "e");
/* 155 */     strClean = strClean.replaceAll("(?sim)a????", "i");
/* 156 */     strClean = strClean.replaceAll("(?sim)?????????", "????");
/* 157 */     strClean = strClean.replaceAll("(?sim)????????", "o");
/* 158 */     strClean = strClean.replaceAll("(?sim)???????????", "e");
/* 159 */     strClean = strClean.replaceAll("(?sim)a???????", "?????");
/* 160 */     strClean = strClean.replaceAll("(?sim)???????????", "\"");
/* 161 */     strClean = strClean.replaceAll("(?sim)????????????? ", "\"");
/* 162 */     strClean = strClean.replaceAll("(?sim)????????????", "\"");
/* 163 */     strClean = strClean.replaceAll("(?sim)???????????????", "...");
/* 164 */     strClean = strClean.replaceAll("(?sim)???????????????", "'");
/* 165 */     strClean = strClean.replaceAll("(?sim)???????????????", "\"");
/* 166 */     strClean = strClean.replaceAll("(?sim)??????????????????", "-");
/* 167 */     strClean = strClean.replaceAll("(?sim)??????????????????", "-");
/* 168 */     strClean = strClean.replaceAll("(?sim)???????????????? ", "-");
/* 169 */     strClean = strClean.replaceAll("(?sim)???????????????-", "");
/* 170 */     strClean = strClean.replaceAll("(?sim)????????????????", "\"");
/* 171 */     strClean = strClean.replaceAll("(?sim)??????????????????", "'");
/* 172 */     strClean = strClean.replaceAll("(?sim)????????", "u");
/* 173 */     strClean = strClean.replaceAll("(?sim)????????", "o");
/* 174 */     strClean = strClean.replaceAll("(?sim)a????", "e");
/* 175 */     strClean = strClean.replaceAll("(?sim)????", "ae");
/* 176 */     strClean = strClean.replaceAll("(?sim)?????", "ae");
/* 177 */     strClean = strClean.replaceAll("(?sim)????????", "u");
/* 178 */     strClean = strClean.replaceAll("(?sim)a?? ", "e");
/* 179 */     strClean = strClean.replaceAll("(?sim)?????", "c");
/* 180 */     strClean = strClean.replaceAll("(?sim)?? ", "c");
/* 181 */     strClean = strClean.replaceAll("(?sim)????", "C");
/* 182 */     strClean = strClean.replaceAll("(?sim)????", "c");
/* 183 */     strClean = strClean.replaceAll("(?sim)????", "eth");
/* 184 */     strClean = strClean.replaceAll("(?sim)?????", "e");
/* 185 */     strClean = strClean.replaceAll("(?sim)????", "e");
/* 186 */     strClean = strClean.replaceAll("(?sim)????", "e");
/* 187 */     strClean = strClean.replaceAll("(?sim)????", "e");
/* 188 */     strClean = strClean.replaceAll("(?sim)????", "e");
/* 189 */     strClean = strClean.replaceAll("(?sim)????", "e");
/* 190 */     strClean = strClean.replaceAll("(?sim)?????", "e");
/* 191 */     strClean = strClean.replaceAll("(?sim)?????", "e");
/* 192 */     strClean = strClean.replaceAll("(?sim)?????", "e");
/* 193 */     strClean = strClean.replaceAll("(?sim)????", "i");
/* 194 */     strClean = strClean.replaceAll("(?sim)?? ", "i");
/* 195 */     strClean = strClean.replaceAll("(?sim)????", "i");
/* 196 */     strClean = strClean.replaceAll("(?sim)????", "i");
/* 197 */     strClean = strClean.replaceAll("(?sim)????", "i");
/* 198 */     strClean = strClean.replaceAll("(?sim)?????", "l");
/* 199 */     strClean = strClean.replaceAll("(?sim)?????", "n");
/* 200 */     strClean = strClean.replaceAll("(?sim)????", "n");
/* 201 */     strClean = strClean.replaceAll("(?sim)????", "n");
/* 202 */     strClean = strClean.replaceAll("(?sim)?????", "n");
/* 203 */     strClean = strClean.replaceAll("(?sim)????", "o");
/* 204 */     strClean = strClean.replaceAll("(?sim)????", "o");
/* 205 */     strClean = strClean.replaceAll("(?sim)????", "o");
/* 206 */     strClean = strClean.replaceAll("(?sim)?????", "o");
/* 207 */     strClean = strClean.replaceAll("(?sim)????", "o");
/* 208 */     strClean = strClean.replaceAll("(?sim)????", "o");
/* 209 */     strClean = strClean.replaceAll("(?sim)?????", "o");
/* 210 */     strClean = strClean.replaceAll("(?sim)????", "o");
/* 211 */     strClean = strClean.replaceAll("(?sim)????", "O");
/* 212 */     strClean = strClean.replaceAll("(?sim)?????", "r");
/* 213 */     strClean = strClean.replaceAll("(?sim)????", "s");
/* 214 */     strClean = strClean.replaceAll("(?sim)?? ", "S");
/* 215 */     strClean = strClean.replaceAll("(?sim)????", "s");
/* 216 */     strClean = strClean.replaceAll("(?sim)????", "ss");
/* 217 */     strClean = strClean.replaceAll("(?sim)????", "u");
/* 218 */     strClean = strClean.replaceAll("(?sim)????", "u");
/* 219 */     strClean = strClean.replaceAll("(?sim)????", "u");
/* 220 */     strClean = strClean.replaceAll("(?sim)????", "u");
/* 221 */     strClean = strClean.replaceAll("(?sim)????", "u");
/* 222 */     strClean = strClean.replaceAll("(?sim)????", "y");
/* 223 */     strClean = strClean.replaceAll("(?sim)????", "y");
/* 224 */     strClean = strClean.replaceAll("(?sim)????", "z");
/* 225 */     strClean = strClean.replaceAll("(?sim)????", "z");
/* 226 */     strClean = strClean.replaceAll("(?sim)????", "Z");
/* 227 */     strClean = strClean.replaceAll("(?sim)?????", "a");
/* 228 */     strClean = strClean.replaceAll("(?sim)??????????????????", "-");
/* 229 */     strClean = strClean.replaceAll("(?sim)&#39", "'");
/* 230 */     strClean = strClean.replaceAll("??", " ");
/* 231 */     return strClean.trim();
/*     */   }
/*     */ }


/* Location:              /home/researchers/Vikram/Vikram/PROJECTS/MOBITO/LE_Revamp/trunk/SetUP/LE_Revamp_DS_V2.0.jar!/com/mobius/le/impl/ContentCrawler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */