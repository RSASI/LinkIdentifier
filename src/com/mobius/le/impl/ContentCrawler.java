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
/*  86 */     strClean = strClean.replaceAll("(?sim)â;", "");
/*  87 */     strClean = strClean.replaceAll("(?sim)&#9658;", "");
/*  88 */     strClean = strClean.replaceAll("(?sim)™", "");
/*  89 */     strClean = strClean.replaceAll("(?sim)€", "");
/*  90 */     strClean = strClean.replaceAll("(?sim)&#x201c", " ");
/*  91 */     strClean = strClean.replaceAll("(?sim)&#x201d", "");
/*  92 */     strClean = strClean.replaceAll("(?sim)&quot;", "");
/*  93 */     strClean = strClean.replaceAll("(?sim);", "");
/*  94 */     strClean = strClean.replaceAll("(?sim)\"", "");
/*  95 */     strClean = strClean.replaceAll("(?sim)…", "");
/*  96 */     strClean = strClean.replaceAll("(?sim)\\[\\]", "");
/*  97 */     strClean = strClean.replaceAll("(?sim)&nbsp;", " ");
/*  98 */     strClean = strClean.replaceAll("(?sim)\\s+", " ");
/*  99 */     strClean = strClean.replaceAll("(?sim)&#9658;", "");
/* 100 */     strClean = strClean.replaceAll("(?sim)&#x201d|Ã¢;|Ã‚|Ã¢|&#9658;|&quot;|Â€|Â™", "");
/* 101 */     strClean = strClean.replaceAll("(?sim)&#x201c", " ");
/* 102 */     strClean = strClean.replaceAll("(?sim)Â ", "\\S");
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
/* 114 */     strClean = strClean.replaceAll("(?sim)&euro;", "â‚¬");
/* 115 */     strClean = strClean.replaceAll("(?sim)&euro;-", "â‚¬");
/* 116 */     strClean = strClean.replaceAll("(?sim)&ldquo;", "\"");
/* 117 */     strClean = strClean.replaceAll("(?sim)&mdash;", "'");
/* 118 */     strClean = strClean.replaceAll("(?sim)&nbsp;", "\\S");
/* 119 */     strClean = strClean.replaceAll("(?sim)&ndash;", "-");
/* 120 */     strClean = strClean.replaceAll("(?sim)&rdquo;", "â€ ");
/* 121 */     strClean = strClean.replaceAll("(?sim)&rsquo;", "'");
/* 122 */     strClean = strClean.replaceAll("(?sim)&rsquo;", "");
/* 123 */     strClean = strClean.replaceAll("(?sim)&rsquo;-", "'");
/* 124 */     strClean = strClean.replaceAll("(?sim)Â¿", "\"");
/* 125 */     strClean = strClean.replaceAll("(?sim)Â¬", "â‚¬");
/* 126 */     strClean = strClean.replaceAll("(?sim)â‚¬â„¢", "'");
/* 127 */     strClean = strClean.replaceAll("(?sim)Ã¡", "a");
/* 128 */     strClean = strClean.replaceAll("(?sim)Ã ", "A");
/* 129 */     strClean = strClean.replaceAll("(?sim)Ã ", "a");
/* 130 */     strClean = strClean.replaceAll("(?sim)Ã€", "A");
/* 131 */     strClean = strClean.replaceAll("(?sim)Ã‚", "");
/* 132 */     strClean = strClean.replaceAll("(?sim)Ã¢", "a");
/* 133 */     strClean = strClean.replaceAll("(?sim)Ã¤", "a");
/* 134 */     strClean = strClean.replaceAll("(?sim)Ã„", "a");
/* 135 */     strClean = strClean.replaceAll("(?sim)Äƒ", "a");
/* 136 */     strClean = strClean.replaceAll("(?sim)Ãƒ", "a");
/* 137 */     strClean = strClean.replaceAll("(?sim)Ã£", "a");
/* 138 */     strClean = strClean.replaceAll("(?sim)Ã…", "a");
/* 139 */     strClean = strClean.replaceAll("(?sim)Ã¥", "a");
/* 140 */     strClean = strClean.replaceAll("(?sim)ÃƒÂ¨", "e");
/* 141 */     strClean = strClean.replaceAll("(?sim)ÃƒÂ´", "o");
/* 142 */     strClean = strClean.replaceAll("(?sim)Ã¢â€šÂ¤", "Â£");
/* 143 */     strClean = strClean.replaceAll("(?sim)Ã¢â€šÂ¬", "â‚¬");
/* 144 */     strClean = strClean.replaceAll("(?sim)aÂ±", "n");
/* 145 */     strClean = strClean.replaceAll("(?sim)ÃƒÂ±", "n");
/* 146 */     strClean = strClean.replaceAll("(?sim)ÃƒÂ±", "n");
/* 147 */     strClean = strClean.replaceAll("(?sim)ÃƒÂ«", "e");
/* 148 */     strClean = strClean.replaceAll("(?sim)aÂ£", "a");
/* 149 */     strClean = strClean.replaceAll("(?sim)Ã‚Â£", "Â£");
/* 150 */     strClean = strClean.replaceAll("(?sim)ÃƒÂ£", "a");
/* 151 */     strClean = strClean.replaceAll("(?sim)ÃƒÂ¤", "a");
/* 152 */     strClean = strClean.replaceAll("(?sim)aÂ§", "c");
/* 153 */     strClean = strClean.replaceAll("(?sim)ÃƒÂ§o", "c");
/* 154 */     strClean = strClean.replaceAll("(?sim)ÃƒÂ©", "e");
/* 155 */     strClean = strClean.replaceAll("(?sim)aÂ®", "i");
/* 156 */     strClean = strClean.replaceAll("(?sim)Ã‚Â®", "Â®");
/* 157 */     strClean = strClean.replaceAll("(?sim)ÃƒÂ¶", "o");
/* 158 */     strClean = strClean.replaceAll("(?sim)Ãƒâ€°", "e");
/* 159 */     strClean = strClean.replaceAll("(?sim)aâ‚¬", "Ã€");
/* 160 */     strClean = strClean.replaceAll("(?sim)Ã¢â‚¬", "\"");
/* 161 */     strClean = strClean.replaceAll("(?sim)Ã¢â‚¬Â ", "\"");
/* 162 */     strClean = strClean.replaceAll("(?sim)Ã¢â‚¬?", "\"");
/* 163 */     strClean = strClean.replaceAll("(?sim)Ã¢â‚¬Â¦", "...");
/* 164 */     strClean = strClean.replaceAll("(?sim)Ã¢â‚¬Ëœ", "'");
/* 165 */     strClean = strClean.replaceAll("(?sim)Ã¢â‚¬Ëœ", "\"");
/* 166 */     strClean = strClean.replaceAll("(?sim)Ã¢â‚¬â€˜", "-");
/* 167 */     strClean = strClean.replaceAll("(?sim)Ã¢â‚¬â€œ", "-");
/* 168 */     strClean = strClean.replaceAll("(?sim)Ã¢â‚¬â€ ", "-");
/* 169 */     strClean = strClean.replaceAll("(?sim)Ã¢â‚¬Â¢-", "");
/* 170 */     strClean = strClean.replaceAll("(?sim)Ã¢â‚¬Å“", "\"");
/* 171 */     strClean = strClean.replaceAll("(?sim)Ã¢â‚¬â„¢", "'");
/* 172 */     strClean = strClean.replaceAll("(?sim)ÃƒÂ¼", "u");
/* 173 */     strClean = strClean.replaceAll("(?sim)ÃƒÂ³", "o");
/* 174 */     strClean = strClean.replaceAll("(?sim)aÂª", "e");
/* 175 */     strClean = strClean.replaceAll("(?sim)Ã¦", "ae");
/* 176 */     strClean = strClean.replaceAll("(?sim)Ã†", "ae");
/* 177 */     strClean = strClean.replaceAll("(?sim)ÃƒÂº", "u");
/* 178 */     strClean = strClean.replaceAll("(?sim)aÅ ", "e");
/* 179 */     strClean = strClean.replaceAll("(?sim)Ä‡", "c");
/* 180 */     strClean = strClean.replaceAll("(?sim)Ä ", "c");
/* 181 */     strClean = strClean.replaceAll("(?sim)ÄŒ", "C");
/* 182 */     strClean = strClean.replaceAll("(?sim)Ã§", "c");
/* 183 */     strClean = strClean.replaceAll("(?sim)Ã°", "eth");
/* 184 */     strClean = strClean.replaceAll("(?sim)Ã‰", "e");
/* 185 */     strClean = strClean.replaceAll("(?sim)Ã©", "e");
/* 186 */     strClean = strClean.replaceAll("(?sim)Ã¨", "e");
/* 187 */     strClean = strClean.replaceAll("(?sim)Ãˆ", "e");
/* 188 */     strClean = strClean.replaceAll("(?sim)Ãª", "e");
/* 189 */     strClean = strClean.replaceAll("(?sim)Ã«", "e");
/* 190 */     strClean = strClean.replaceAll("(?sim)Ä›", "e");
/* 191 */     strClean = strClean.replaceAll("(?sim)Ä•", "e");
/* 192 */     strClean = strClean.replaceAll("(?sim)Ä™", "e");
/* 193 */     strClean = strClean.replaceAll("(?sim)Ã­", "i");
/* 194 */     strClean = strClean.replaceAll("(?sim)Ã ", "i");
/* 195 */     strClean = strClean.replaceAll("(?sim)Ã¬", "i");
/* 196 */     strClean = strClean.replaceAll("(?sim)Ã®", "i");
/* 197 */     strClean = strClean.replaceAll("(?sim)Ã¯", "i");
/* 198 */     strClean = strClean.replaceAll("(?sim)Å‚", "l");
/* 199 */     strClean = strClean.replaceAll("(?sim)Å„", "n");
/* 200 */     strClean = strClean.replaceAll("(?sim)Åˆ", "n");
/* 201 */     strClean = strClean.replaceAll("(?sim)Ã±", "n");
/* 202 */     strClean = strClean.replaceAll("(?sim)Ã‘", "n");
/* 203 */     strClean = strClean.replaceAll("(?sim)Ã³", "o");
/* 204 */     strClean = strClean.replaceAll("(?sim)Ã²", "o");
/* 205 */     strClean = strClean.replaceAll("(?sim)Ã´", "o");
/* 206 */     strClean = strClean.replaceAll("(?sim)Ã”", "o");
/* 207 */     strClean = strClean.replaceAll("(?sim)Ã¶", "o");
/* 208 */     strClean = strClean.replaceAll("(?sim)Ãµ", "o");
/* 209 */     strClean = strClean.replaceAll("(?sim)Å‘", "o");
/* 210 */     strClean = strClean.replaceAll("(?sim)Ã¸", "o");
/* 211 */     strClean = strClean.replaceAll("(?sim)Ã˜", "O");
/* 212 */     strClean = strClean.replaceAll("(?sim)Å™", "r");
/* 213 */     strClean = strClean.replaceAll("(?sim)Å¡", "s");
/* 214 */     strClean = strClean.replaceAll("(?sim)Å ", "S");
/* 215 */     strClean = strClean.replaceAll("(?sim)ÅŸ", "s");
/* 216 */     strClean = strClean.replaceAll("(?sim)ÃŸ", "ss");
/* 217 */     strClean = strClean.replaceAll("(?sim)Ãº", "u");
/* 218 */     strClean = strClean.replaceAll("(?sim)Ã¹", "u");
/* 219 */     strClean = strClean.replaceAll("(?sim)Ã»", "u");
/* 220 */     strClean = strClean.replaceAll("(?sim)Ã¼", "u");
/* 221 */     strClean = strClean.replaceAll("(?sim)Ãœ", "u");
/* 222 */     strClean = strClean.replaceAll("(?sim)Ã½", "y");
/* 223 */     strClean = strClean.replaceAll("(?sim)Ã¿", "y");
/* 224 */     strClean = strClean.replaceAll("(?sim)Å¼", "z");
/* 225 */     strClean = strClean.replaceAll("(?sim)Å¾", "z");
/* 226 */     strClean = strClean.replaceAll("(?sim)Å½", "Z");
/* 227 */     strClean = strClean.replaceAll("(?sim)Ä…", "a");
/* 228 */     strClean = strClean.replaceAll("(?sim)Ã¢â‚¬â€œ", "-");
/* 229 */     strClean = strClean.replaceAll("(?sim)&#39", "'");
/* 230 */     strClean = strClean.replaceAll(" ", " ");
/* 231 */     return strClean.trim();
/*     */   }
/*     */ }


/* Location:              /home/researchers/Vikram/Vikram/PROJECTS/MOBITO/LE_Revamp/trunk/SetUP/LE_Revamp_DS_V2.0.jar!/com/mobius/le/impl/ContentCrawler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */