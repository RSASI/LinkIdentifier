/*     */ package com.mobius.le.impl;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import java.util.regex.Matcher;
/*     */ import java.util.regex.Pattern;
/*     */ import java.util.regex.PatternSyntaxException;
/*     */ import org.apache.log4j.Logger;
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
/*     */ 
/*     */ public class KeyLookup
/*     */ {
/*  30 */   public static List<String> linkText = new ArrayList();
/*     */   
/*  32 */   public static String contentNormalization(String content) { String normalizedContent = content;
/*     */     try {
/*  34 */       normalizedContent = normalizedContent.replaceAll("(?si)<script[^<>]*?>.*?</script>", " ");
/*  35 */       normalizedContent = normalizedContent.replaceAll("(?si)<iframe[^<>]*?>.*?</iframe>", " ");
/*  36 */       normalizedContent = normalizedContent.replaceAll("(?si)<iframe[^<>]*?>", "");
/*  37 */       normalizedContent = normalizedContent.replaceAll("&#65533;", "'");
/*  38 */       normalizedContent = normalizedContent.replaceAll("(?si)<img[^<>]*?>|</img>", " ");
/*  39 */       normalizedContent = normalizedContent.replaceAll("(?si)<style[^<>]*?>.*?</style>", " ");
/*  40 */       normalizedContent = normalizedContent.replaceAll("(?si)<noscript[^<>]*?>.*?</noscript>", " ");
/*  41 */       normalizedContent = normalizedContent.replaceAll("<noscript/>", "");
/*     */       
/*  43 */       normalizedContent = normalizedContent.replaceAll("(?sim)<head>.*?</head>", " ");
/*  44 */       normalizedContent = normalizedContent.replaceAll("(?sim)<!--[^<>]*>", " ");
/*  45 */       normalizedContent = normalizedContent.replaceAll("(?sim)<!--.*?-->", " ");
/*  46 */       normalizedContent = normalizedContent.replaceAll("(?sim)\\s\\s+", "\n");
/*  47 */       normalizedContent = normalizedContent.replaceAll("(?sim)&nbsp;", " ");
/*  48 */       normalizedContent = normalizedContent.replaceAll("(?sim)<.*?>", " ");
/*     */     }
/*     */     catch (Exception e)
/*     */     {
/*  52 */       Loader.log.error(Loader.appendLog(""), e);
/*     */     }
/*  54 */     return normalizedContent;
/*     */   }
/*     */   
/*     */   public static String RegexMatcher(String source, String patern, int group_no) {
/*  58 */     String resultString = "";String result = "";
/*     */     try {
/*  60 */       Pattern regex1 = Pattern.compile(patern, 234);
/*     */       
/*  62 */       Matcher regexMatcher1 = regex1.matcher(source);
/*  63 */       while (regexMatcher1.find())
/*     */       {
/*  65 */         result = regexMatcher1.group(group_no);
/*  66 */         result = result.trim();
/*  67 */         if ((!result.toLowerCase().endsWith(".exe")) || (!result.toLowerCase().endsWith(".xml")) || (!result.toLowerCase().endsWith(".rpm")) || (!result.toLowerCase().endsWith(".pdf")) || (!result.toLowerCase().endsWith(".xls")) || (!result.toLowerCase().endsWith(".doc")) || (!result.toLowerCase().endsWith(".jpg")) || (!result.toLowerCase().endsWith(".png")) || (!result.toLowerCase().endsWith(".flv")) || (!result.toLowerCase().endsWith(".avi")) || (!result.toLowerCase().endsWith(".f4v")) || (!result.toLowerCase().endsWith(".mp4")) || (!result.toLowerCase().endsWith(".mpg")) || (!result.toLowerCase().endsWith(".mpeg")) || (!result.toLowerCase().endsWith(".rar")) || (!result.toLowerCase().endsWith(".zip")) || (!result.toLowerCase().endsWith(".gif")) || (!result.toLowerCase().endsWith(".jpeg")) || (!result.toLowerCase().endsWith(".mp3")) || (!result.toLowerCase().endsWith(".mp2")) || (!result.toLowerCase().endsWith(".3gp")) || (!result.toLowerCase().endsWith(".m4v")) || (!result.toLowerCase().endsWith(".flr")) || (!result.toLowerCase().endsWith(".fla")) || (!result.toLowerCase().endsWith(".mng")) || (!result.toLowerCase().endsWith(".mov")) || (!result.toLowerCase().endsWith(".mpe")) || (!result.toLowerCase().endsWith(".swf")) || (!result.toLowerCase().endsWith(".wmv")))
/*     */         {
/*  69 */           resultString = resultString + "," + result;
/*     */         }
/*     */       }
/*     */     }
/*     */     catch (PatternSyntaxException localPatternSyntaxException) {}
/*     */     
/*  75 */     return resultString.replaceAll("(?sim)\\A,", "");
/*     */   }
/*     */   
/*     */ 
/*     */   public static boolean IsNotFound(String Source)
/*     */   {
/*  81 */     for (String keys : DepthManager.pageNotFoundList)
/*     */     {
/*  83 */       if (Source.contains(keys))
/*     */       {
/*  85 */         return true;
/*     */       }
/*     */     }
/*     */     
/*  89 */     return false;
/*     */   }
/*     */   
/*     */ 
/*     */   public static List<String> LinkText(String source)
/*     */   {
/*  95 */     Pattern regex = Pattern.compile("<a\\s*href.*?>(.*?)</a>", 234);
/*     */     
/*  97 */     Matcher regexMatcher = regex.matcher(source);
/*  98 */     while (regexMatcher.find())
/*     */     {
/* 100 */       linkText.add(regexMatcher.group().replaceAll("(?sim)<a.*?><img src.*?</a>", "").replaceAll("(?sim)<.*?>", ""));
/*     */     }
/* 102 */     return linkText;
/*     */   }
/*     */   
/*     */   public static String SiteType(String site_content) {
/* 106 */     String match = "";
/* 107 */     String s_type = "";
/* 108 */     match = RegexMatch(site_content, "((<iframe[^<>]*>.*?</iframe>)|<frameset[^<>]*>.*?</frameset>)", 0);
/* 109 */     if (!match.equals("")) {
/* 110 */       s_type = "Frame Site";
/* 111 */     } else if (match.equals("")) {
/* 112 */       match = RegexMatch(site_content, "(<embed[^<>]*flash[^<>]*)|<object[^<>]*flash[^<>]*", 0);
/* 113 */       if (!match.equals("")) {
/*     */         try {
/* 115 */           String flash_content = site_content;
/* 116 */           flash_content = ContentNormalization(flash_content);
/* 117 */           flash_content = flash_content.replaceAll("(?sim)<.*?>", "");
/* 118 */           flash_content = flash_content.trim();
/* 119 */           if (flash_content.length() > 1000) {
/* 120 */             s_type = "Partially Flash Site";
/*     */           } else {
/* 122 */             s_type = "Flash Site";
/*     */           }
/*     */         }
/*     */         catch (PatternSyntaxException localPatternSyntaxException) {}
/*     */       }
/*     */     }
/* 128 */     return s_type;
/*     */   }
/*     */   
/* 131 */   public static String RegexMatch(String source, String patern, int group_no) { String resultString = "";
/*     */     try {
/* 133 */       Pattern regex1 = Pattern.compile(patern, 234);
/*     */       
/* 135 */       Matcher regexMatcher1 = regex1.matcher(source);
/* 136 */       if (regexMatcher1.find()) {
/* 137 */         resultString = regexMatcher1.group(group_no);
/*     */       }
/*     */     }
/*     */     catch (PatternSyntaxException localPatternSyntaxException) {}
/* 141 */     return resultString;
/*     */   }
/*     */   
/*     */   public static String ContentNormalization(String content) {
/* 145 */     String normalizedContent = content;
/*     */     try {
/* 147 */       normalizedContent = normalizedContent.replaceAll("(?si)<script[^<>]*?>.*?</script>", "");
/* 148 */       normalizedContent = normalizedContent.replaceAll("&#65533;", "'");
/* 149 */       normalizedContent = normalizedContent.replaceAll("(?si)<img[^<>]*?>|</img>", "");
/* 150 */       normalizedContent = normalizedContent.replaceAll("(?si)<style[^<>]*?>.*?</style>", "");
/* 151 */       normalizedContent = normalizedContent.replaceAll("(?si)<noscript[^<>]*?>.*?</noscript>", "");
/* 152 */       normalizedContent = normalizedContent.replaceAll("<noscript/>", "");
/* 153 */       normalizedContent = normalizedContent.replaceAll("display\\s*:\\s*none\\s*", "");
/* 154 */       normalizedContent = normalizedContent.replaceAll("(?sim)<!--[^<>]*>", "");
/* 155 */       normalizedContent = normalizedContent.replaceAll("(?sim)<!--.*?-->", "");
/* 156 */       normalizedContent = normalizedContent.replaceAll("(?sim)\\s\\s+", "");
/* 157 */       normalizedContent = normalizedContent.replaceAll("(?sim)&nbsp;", " ");
/* 158 */       normalizedContent = normalizedContent.replaceAll("(?sim)//<!\\[CDATA\\[.*?//\\]\\]>", "");
/*     */     }
/*     */     catch (Exception e) {
/* 161 */       Loader.log.error(Loader.appendLog(""), e);
/*     */     }
/* 163 */     return normalizedContent;
/*     */   }
/*     */   
/*     */   public static String LinkTextClean(String linktext) {
/* 167 */     String ltext = "";
/*     */     
/*     */ 
/*     */     try
/*     */     {
/* 172 */       ltext = linktext.replaceAll("(?sim)&nbsp;", "");
/* 173 */       ltext = ltext.replaceAll("(?sim)&amp;", "");
/*     */     }
/*     */     catch (Exception e) {
/* 176 */       Loader.log.error(Loader.appendLog(""), e);
/*     */     }
/* 178 */     return ltext;
/*     */   }
/*     */   
/* 181 */   public static String Dia_Clean(String strClean) { strClean = strClean.replaceAll("<.*?>", "");
/* 182 */     strClean = strClean.replaceAll("&reg;", "");
/* 183 */     strClean = strClean.replaceAll("&nbsp;", " ");
/* 184 */     strClean = strClean.replaceAll("\\s+", " ");
/* 185 */     strClean = strClean.replaceAll("&amp", "&");
/* 186 */     strClean = strClean.replaceAll("&#9658;", "");
/* 187 */     strClean = strClean.replaceAll("??;", "");
/* 188 */     strClean = strClean.replaceAll("&#9658;", "");
/* 189 */     strClean = strClean.replaceAll("???", "");
/* 190 */     strClean = strClean.replaceAll("???", "");
/* 191 */     strClean = strClean.replaceAll("&#x201c", " ");
/* 192 */     strClean = strClean.replaceAll("&#x201d", "");
/* 193 */     strClean = strClean.replaceAll("&quot;", "");
/* 194 */     strClean = strClean.replaceAll(";", "");
/* 195 */     strClean = strClean.replaceAll("\"", "");
/* 196 */     strClean = strClean.replaceAll("???", "");
/* 197 */     strClean = strClean.replaceAll("\\[\\]", "");
/* 198 */     strClean = strClean.replaceAll("<.*?>", "");
/* 199 */     strClean = strClean.replaceAll("&nbsp;", " ");
/* 200 */     strClean = strClean.replaceAll("\\s+", " ");
/* 201 */     strClean = strClean.replaceAll("&#9658;", "");
/* 202 */     strClean = strClean.replaceAll("&#x201d|????;|?????|????|&#9658;|&quot;|?????|?????", "");
/* 203 */     strClean = strClean.replaceAll("&#x201c", " ");
/* 204 */     strClean = strClean.replaceAll("?? ", "\\S");
/* 205 */     strClean = strClean.replaceAll("\"\"", "\"");
/* 206 */     strClean = strClean.replaceAll("&#039;", "'");
/* 207 */     strClean = strClean.replaceAll("&#233", "e");
/* 208 */     strClean = strClean.replaceAll("&#235", "e");
/* 209 */     strClean = strClean.replaceAll("&#8211;", "-");
/* 210 */     strClean = strClean.replaceAll("&#8213;", "-");
/* 211 */     strClean = strClean.replaceAll("&#8217;", "'");
/* 212 */     strClean = strClean.replaceAll("&#8220;", "\"");
/* 213 */     strClean = strClean.replaceAll("&#8221;", "\"");
/* 214 */     strClean = strClean.replaceAll("&amp;", "&");
/* 215 */     strClean = strClean.replaceAll("&bull;", "6");
/* 216 */     strClean = strClean.replaceAll("&euro;", "???????");
/* 217 */     strClean = strClean.replaceAll("&euro;-", "???????");
/* 218 */     strClean = strClean.replaceAll("&ldquo;", "\"");
/* 219 */     strClean = strClean.replaceAll("&mdash;", "'");
/* 220 */     strClean = strClean.replaceAll("&nbsp;", "\\S");
/* 221 */     strClean = strClean.replaceAll("&ndash;", "-");
/* 222 */     strClean = strClean.replaceAll("&rdquo;", "????? ");
/* 223 */     strClean = strClean.replaceAll("&rsquo;", "'");
/* 224 */     strClean = strClean.replaceAll("&rsquo;", "");
/* 225 */     strClean = strClean.replaceAll("&rsquo;-", "'");
/* 226 */     strClean = strClean.replaceAll("????", "\"");
/* 227 */     strClean = strClean.replaceAll("????", "???????");
/* 228 */     strClean = strClean.replaceAll("??????????????", "'");
/* 229 */     strClean = strClean.replaceAll("????", "a");
/* 230 */     strClean = strClean.replaceAll("?? ", "A");
/* 231 */     strClean = strClean.replaceAll("?? ", "a");
/* 232 */     strClean = strClean.replaceAll("?????", "A");
/* 233 */     strClean = strClean.replaceAll("?????", "");
/* 234 */     strClean = strClean.replaceAll("????", "a");
/* 235 */     strClean = strClean.replaceAll("????", "a");
/* 236 */     strClean = strClean.replaceAll("?????", "a");
/* 237 */     strClean = strClean.replaceAll("????", "a");
/* 238 */     strClean = strClean.replaceAll("????", "a");
/* 239 */     strClean = strClean.replaceAll("????", "a");
/* 240 */     strClean = strClean.replaceAll("?????", "a");
/* 241 */     strClean = strClean.replaceAll("????", "a");
/* 242 */     strClean = strClean.replaceAll("????????", "e");
/* 243 */     strClean = strClean.replaceAll("????????", "o");
/* 244 */     strClean = strClean.replaceAll("???????????????", "????");
/* 245 */     strClean = strClean.replaceAll("???????????????", "???????");
/* 246 */     strClean = strClean.replaceAll("a????", "n");
/* 247 */     strClean = strClean.replaceAll("????????", "n");
/* 248 */     strClean = strClean.replaceAll("????????", "n");
/* 249 */     strClean = strClean.replaceAll("????????", "e");
/* 250 */     strClean = strClean.replaceAll("a????", "a");
/* 251 */     strClean = strClean.replaceAll("?????????", "????");
/* 252 */     strClean = strClean.replaceAll("????????", "a");
/* 253 */     strClean = strClean.replaceAll("????????", "a");
/* 254 */     strClean = strClean.replaceAll("a????", "c");
/* 255 */     strClean = strClean.replaceAll("????????o", "c");
/* 256 */     strClean = strClean.replaceAll("????????", "e");
/* 257 */     strClean = strClean.replaceAll("a????", "i");
/* 258 */     strClean = strClean.replaceAll("?????????", "????");
/* 259 */     strClean = strClean.replaceAll("????????", "o");
/* 260 */     strClean = strClean.replaceAll("???????????", "e");
/* 261 */     strClean = strClean.replaceAll("a???????", "?????");
/* 262 */     strClean = strClean.replaceAll("???????????", "\"");
/* 263 */     strClean = strClean.replaceAll("????????????? ", "\"");
/* 264 */     strClean = strClean.replaceAll("????????????", "\"");
/* 265 */     strClean = strClean.replaceAll("???????????????", "...");
/* 266 */     strClean = strClean.replaceAll("???????????????", "'");
/* 267 */     strClean = strClean.replaceAll("???????????????", "\"");
/* 268 */     strClean = strClean.replaceAll("??????????????????", "-");
/* 269 */     strClean = strClean.replaceAll("??????????????????", "-");
/* 270 */     strClean = strClean.replaceAll("???????????????? ", "-");
/* 271 */     strClean = strClean.replaceAll("???????????????-", "");
/* 272 */     strClean = strClean.replaceAll("????????????????", "\"");
/* 273 */     strClean = strClean.replaceAll("??????????????????", "'");
/* 274 */     strClean = strClean.replaceAll("????????", "u");
/* 275 */     strClean = strClean.replaceAll("????????", "o");
/* 276 */     strClean = strClean.replaceAll("a????", "e");
/* 277 */     strClean = strClean.replaceAll("????", "ae");
/* 278 */     strClean = strClean.replaceAll("?????", "ae");
/* 279 */     strClean = strClean.replaceAll("????????", "u");
/* 280 */     strClean = strClean.replaceAll("a?? ", "e");
/* 281 */     strClean = strClean.replaceAll("?????", "c");
/* 282 */     strClean = strClean.replaceAll("?? ", "c");
/* 283 */     strClean = strClean.replaceAll("????", "C");
/* 284 */     strClean = strClean.replaceAll("????", "c");
/* 285 */     strClean = strClean.replaceAll("????", "eth");
/* 286 */     strClean = strClean.replaceAll("?????", "e");
/* 287 */     strClean = strClean.replaceAll("????", "e");
/* 288 */     strClean = strClean.replaceAll("????", "e");
/* 289 */     strClean = strClean.replaceAll("????", "e");
/* 290 */     strClean = strClean.replaceAll("????", "e");
/* 291 */     strClean = strClean.replaceAll("????", "e");
/* 292 */     strClean = strClean.replaceAll("?????", "e");
/* 293 */     strClean = strClean.replaceAll("?????", "e");
/* 294 */     strClean = strClean.replaceAll("?????", "e");
/* 295 */     strClean = strClean.replaceAll("????", "i");
/* 296 */     strClean = strClean.replaceAll("?? ", "i");
/* 297 */     strClean = strClean.replaceAll("????", "i");
/* 298 */     strClean = strClean.replaceAll("????", "i");
/* 299 */     strClean = strClean.replaceAll("????", "i");
/* 300 */     strClean = strClean.replaceAll("?????", "l");
/* 301 */     strClean = strClean.replaceAll("?????", "n");
/* 302 */     strClean = strClean.replaceAll("????", "n");
/* 303 */     strClean = strClean.replaceAll("????", "n");
/* 304 */     strClean = strClean.replaceAll("?????", "n");
/* 305 */     strClean = strClean.replaceAll("????", "o");
/* 306 */     strClean = strClean.replaceAll("????", "o");
/* 307 */     strClean = strClean.replaceAll("????", "o");
/* 308 */     strClean = strClean.replaceAll("?????", "o");
/* 309 */     strClean = strClean.replaceAll("????", "o");
/* 310 */     strClean = strClean.replaceAll("????", "o");
/* 311 */     strClean = strClean.replaceAll("?????", "o");
/* 312 */     strClean = strClean.replaceAll("????", "o");
/* 313 */     strClean = strClean.replaceAll("????", "O");
/* 314 */     strClean = strClean.replaceAll("?????", "r");
/* 315 */     strClean = strClean.replaceAll("????", "s");
/* 316 */     strClean = strClean.replaceAll("?? ", "S");
/* 317 */     strClean = strClean.replaceAll("????", "s");
/* 318 */     strClean = strClean.replaceAll("????", "ss");
/* 319 */     strClean = strClean.replaceAll("????", "u");
/* 320 */     strClean = strClean.replaceAll("????", "u");
/* 321 */     strClean = strClean.replaceAll("????", "u");
/* 322 */     strClean = strClean.replaceAll("????", "u");
/* 323 */     strClean = strClean.replaceAll("????", "u");
/* 324 */     strClean = strClean.replaceAll("????", "y");
/* 325 */     strClean = strClean.replaceAll("????", "y");
/* 326 */     strClean = strClean.replaceAll("????", "z");
/* 327 */     strClean = strClean.replaceAll("????", "z");
/* 328 */     strClean = strClean.replaceAll("????", "Z");
/* 329 */     strClean = strClean.replaceAll("?????", "a");
/* 330 */     strClean = strClean.replaceAll("??????????????????", "-");
/* 331 */     strClean = strClean.replaceAll("&#39", "'");
/*     */     
/* 333 */     return strClean.trim();
/*     */   }
/*     */ }


/* Location:              /home/researchers/Vikram/Vikram/PROJECTS/MOBITO/LE_Revamp/trunk/SetUP/LE_Revamp_DS_V2.0.jar!/com/mobius/le/impl/KeyLookup.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */