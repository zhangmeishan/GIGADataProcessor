

           README File for the GIGAWORD CHINESE TEXT CORPUS
           ================================================

                            Fifth Edition 
                            =============
                              LDC2011T13
                            =============

INTRODUCTION
------------

The Gigaword Chinese Corpus is a comprehensive archive of newswire
text data that has been acquired over several years by the Linguistic
Data Consortium (LDC), at the University of Pennsylvania.  This is the
fifth edition of the Gigaword Chinese Corpus.

This edition includes all of the contents in the previous edition of
the Chinese Gigaword corpus (LDC2009T27) as well as new data collected
after the publication of that edition.  

The eight distinct international sources of Chinese newswire included
in this edition are the following:

 - Agence France Presse                              (afp_cmn)
 - Central News Agency, Taiwan                       (cna_cmn)
 - Central News Service                              (cns_cmn)
 - Guangming Daily                                   (gmw_cmn)
 - People's Daily                                    (pda_cmn)
 - People's Liberation Army Daily                    (pla_cmn)
 - Xinhua News Agency                                (xin_cmn)
 - Zaobao Newspaper                                  (zbn_cmn)

The seven-letter codes in the parentheses above are used for the
directory names and data files for each source, and are also used (in
ALL_CAPS) as part of the unique DOC "id" string assigned to each news
article.


WHAT'S NEW IN THE FIFTH EDITION
--------------------------------

- Two years worth of new articles (January 2009 through December 2010)
  have been added to the Xinhua, Agence France Presse, CNA, Guangming 
  Daily, Central News Service , and People's Liberation Army Daily data 
  sets.  People's Daily has had data added from late June 2009 through 
  December 2010.

- ZBN and CNA data included in previous releases were found to contain
  non-normalized full-width characters. These files were normalized to
  correct this issue.

CHARACTER ENCODING
------------------

The original data received by the LDC from AFP, People's Liberation 
Army Daily, Xinhua, and Zaobao were encoded in GB-2312, those from CNA 
were in Big-5, and those from GMW, CNS, and People's Daily were in a 
combination of GB-2312 and GB-18030.  To avoid the problems and confusion 
that could result from differences in character-set specifications, all 
text files in this corpus have been converted to UTF-8 character encoding.

Researchers who have concerns about the comparability and
compatibility of text data from GB and Big-5 sources should consult
The Unicode Standard (published by the Unicode Consortium,
http://www.unicode.org), paying special attention to Chapter 10, "East
Asian Scripts", and Appendix A, "Han Unification History".

Owing to the use of UTF-8, the SGML tagging within each file
(described in detail in the next section) shows up as lines of
single-byte-per-character (ASCII) text, whereas lines of actual text
data, including article headlines and datelines, contain a mixture of
single-byte and multi-byte characters.

Both Big-5 and GB are designed to support ASCII single-byte character
data as well as 2-byte Chinese characters; in addition, each of these
coding standards has a section of the 2-byte character space devoted
to "full-width" renderings of the printable ASCII characters.  For
example, the digits 0-9 can be presented as either single-byte ASCII
codes or as 2-byte full-width codes, as shown in the following table:

  Digit		ASCII	GB 2-byte	Big-5 2-byte
  Character	byte	code-point	code-point
  --------------------------------------------------
    0		0x30	0xA3C0		0xA2AF
    1		0x31	0xA3C1		0xA2B0
    2		0x32	0xA3C2		0xA2B1
    3		0x33	0xA3C3		0xA2B2
    4		0x34	0xA3C4		0xA2B3
    5		0x35	0xA3C5		0xA2B4
    6		0x36	0xA3C6		0xA2B5
    7		0x37	0xA3C7		0xA2B6
    8		0x38	0xA3C8		0xA2B7
    9		0x39	0xA3C9		0xA2B8

and similarly for the upper- and lower-case alphabet characters,
brackets, quotation marks and punctuation.  We found that some sources 
showed evidence of somewhat free variation between single-and two-byte 
forms when presenting alphanumerics, etc, within the text data.  
Although the Unicode Standard provides an analogous portion of
its code table to these full-width characters, we decided instead to
eliminate this form of variation in the data: wherever the original
data contained 2-byte versions of characters having exact correlates
in the single-byte ASCII table, we replaced the 2-byte character with
the single-byte ASCII equivalent.  As a result, many lines of text
data contain a mix of multi-byte Chinese and single-byte ASCII
content.  Of course, since all the data is now presented in UTF-8
encoding, this mixture is a natural property of the data, which any
UTF-8-aware process will handle without difficulty.

We also found that all sources use a handful of "accented" alphabetics
and other special characters common to European character sets.  When
converted to UTF8, these characters assume their "normal" places in
the Unicode table -- e.g. the "raised circle", used as a "degrees"
mark in temperatures or latitude/longitude coordinates, can be found
in the Xinhua data rendered as U00B0 (which in UTF8 form comes out as
the two-byte sequence 0xC2 0xB0).  Apart from these rare cases, all
characters in the text are either single-byte ASCII or multi-byte
Chinese.


DATA FORMAT AND SGML MARKUP
---------------------------

Each data file name consists of the 7-letter prefix (e.g., xin_cmn)
and an underscore character ('_') followed by a 6-digit date
(representing the year and month during which the file contents were
originally published by the respective news source), followed by a
".gz" file extension, indicating that the file contents have been
compressed using the GNU "gzip" compression utility (RFC 1952).  So,
each file contains all the usable data received by LDC for the given
month from the given news source.

All text data are presented in SGML form, using a very simple, minimal
markup structure.  The file "gigaword_c.dtd" in the "docs" directory
provides the formal "Document Type Declaration" for parsing the SGML
content.  The corpus has been fully validated by a standard SGML
parser utility (nsgmls), using this DTD file.

The markup structure, common to all data files, can be summarized as
follows:

<DOC id="..." type="..." >
<HEADLINE>
The Headline Element is Optional -- not all DOCs have one
</HEADLINE>
<DATELINE>
The Dateline Element is Optional -- not all DOCs have one
</DATELINE>
<TEXT>
<P>
Paragraph tags are only used if the 'type' attribute of the DOC
happens to be "story" -- more on the 'type' attribute below...
</P>
<P>
Note that all data files use the UNIX-standard "\n" form of line
termination, and text lines are generally wrapped to a width of 40
characters or less.
</P>
</TEXT>
</DOC>

For every "opening" tag (DOC, HEADLINE, DATELINE, TEXT, P), there is a
corresponding "closing" tag -- always.  The attribute values in the
DOC tag are always presented within double-quotes; the "id=" attribute
of DOC consists of the 7-letter source and language ID (in CAPS), an
underscore, an 8-digit date string representing the date of the story
(YYYYMMDD), a period, and a 4-digit sequence number starting at "0001"
for each date (e.g. "XIN_CMN_199501.0001"); in this way, every DOC in
the corpus is uniquely identifiable by the id string.

Every SGML tag is presented alone on one line, separate from other
tags, and from the text content (so a simple process like the UNIX
"grep -v '<'" will eliminate all tags, and retain all the text
content).

The structure shown above represents some notable differences relative
to the markup strategy employed in previous LDC text corpora; these
are intended to facilitate bulk processing of the present corpus.  The
major differences are:

 - Earlier corpora usually organized the data as one file per day, or
   limited the average file size to one megabyte (MB).

Typical compressed file sizes in the current corpus range from about 500
KB (2003 CNA data) to about 10 MB (2001-2 CNA data); this equates
to a range of about 1.5 to 27 MB when the data are uncompressed.  In
general, these files are not intended for use with interactive text
editors or word processing software (though many such programs are
likely to work reasonably well with these files).  Rather, it's
expected that the files will be used as input to programs that are
geared to dealing with data in such quantities, for filtering,
conditioning, indexing, statistical summary, etc.  (The LDC can
provide open source software, mostly written in Perl, for extracting
DOCs from such data files, using the "id" string or other search
criteria for story selection; see http://www.ldc.upenn.edu/Using/ .)

 - Earlier corpora tended to use different markup outlines (different
   tag sets) depending on the source of the data, because different
   sources came to us with different structural properties, and we had
   chosen to preserve these as much as possible (even though many
   elements of the delivered structure may have been meaningless for
   research use).

The present corpus uses only the information structure that is common
to all sources and serves a clear function: headline, dateline, and
core news content (usually containing paragraphs).  The "dateline" is
a brief string typically found at the beginning of the first paragraph
in each news story, giving the location the report is coming from, and
sometimes the news service and/or date; since this content is not part
of the initial sentence, we separate it from the first paragraph (this
was not done in previous corpora).

 - Earlier corpora tended to include "custom" SGML entity references,
   which were intended to preserve things like special punctuation or
   typesetting instructions (e.g. "&QL;", "&UR;", "&MD;", etc).

The present corpus uses only three SGML entity reference: 
 - ``&amp;'' represents the literal ampersand "&" character
 - ``&lt;''  represents the literal open-angle bracket "<"
 - ``&gt;''  represents the literal close-angle bracket ">"
All other specialized control characters have been filtered out.

 - In earlier corpora, newswire data were presented as streams of
   undifferentiated "DOC" units; depending on the source and corpus,
   varying amounts of quality checking and filtering were done to
   eliminate noisy or unsuitable content (e.g. test messages).

For this release, all sources have received a uniform treatment in
terms of quality control, and we have applied a rudimentary (and
_approximate_) categorization of DOC units into four distinct "types".
The classification is indicated by the `` type="string" '' attribute
that is included in each opening ``DOC'' tag.  The four types are:

* story : This is by far the most frequent type, and it represents the
  most typical newswire item: a coherent report on a particular topic
  or event, consisting of paragraphs and full sentences.  As indicated
  above, the paragraph tag "<P>" is found only in DOCs of this type;
  in the other types described below, the text content is rendered
  with no additional tags or special characters -- just lines of ASCII
  tokens separated by whitespace.

* multi : This type of DOC contains a series of unrelated "blurbs",
  each of which briefly describes a particular topic or event; this is
  typically applied to DOCs that contain "summaries of todays news",
  "news briefs in ... (some general area like finance or sports)", and
  so on.  Each paragraph-like blurb by itself is coherent, but it does
  not bear any necessary relation of topicality or continuity relative
  to it neighbors.

* advis : (short for "advisory") These are DOCs which the news service
  addresses to news editors -- they are not intended for publication
  to the "end users" (the populations who read the news); as a result,
  DOCs of this type tend to contain obscure abbreviations and phrases,
  which are familiar to news editors, but may be meaningless to the
  general public.  We also find a lot of formulaic, repetitive content
  in DOCs of this type (contact phone numbers, etc).

* other : This represents DOCs that clearly do not fall into any of
  the above types -- in general, items of this type are intended for
  broad circulation (they are not advisories), they may be topically
  coherent (unlike "multi" type DOCS), and they typically do not
  contain paragraphs or sentences (they aren't really "stories");
  these are things like lists of sports scores, stock prices,
  temperatures around the world, and so on.

The general strategy for categorizing DOCs into these four classes
was, for each source, to discover the most common and frequent clues
in the text stream that correlated with the three "non-story" types,
and to apply the appropriate label for the ``type=...'' attribute
whenever the DOC displayed one of these specific clues.  When none of
the known clues was in evidence, the DOC was classified as a "story".

This means that the most frequent classification error will tend to be
the use of `` type="story" '' on DOCs that are actually some other
type.  But the number of such errors should be fairly small, compared
to the number of "non-story" DOCs that are correctly tagged as such.

Note that the markup was applied algorithmically, using logic that was
based on less-than-complete knowledge of the data.  For the most part,
the HEADLINE, DATELINE and TEXT tags have their intended content; but
due to the inherent variability (and the inevitable source errors) in
the data, users may find occasional mishaps where the headline and/or
dateline were not successfully identified (hence show up within TEXT),
or where an initial sentence or paragraph has been mistakenly tagged
as the headline or dateline.


DATA QUANTITIES
---------------

The "docs" directory contains a set of plain-text tables (datastats_*)
that describe the quantities of data by source and month (i.e. by
file), broken down according to the four "type" categories.  The
overall totals for each source are summarized below.  Note that the
"Totl-MB" numbers show the amount of data you get when the files are
uncompressed; the "Gzip-MB" column shows totals for compressed file
sizes as stored on the DVD-ROM; the "K-wrds" numbers are actually the
number of Chinese characters (there is no notion of "space separated
word tokens" in Chinese, and for these tallies, we are not counting
ASCII or other non-Chinese characters in the data):

  Source  #Files  Gzip-MB  Totl-MB   K-wrds    #DOCs
  --------------------------------------------------
  afp_cmn    123       88      195     4225   168952
  cna_cmn    236     1565     3558    79085  2559520
  cns_cmn     50      759     1658    27315   700082
  gmw_cmn     50      160      357     5737   122375
  pda_cmn     44      385      867    14859   363460
  pla_cmn     50      140      311     5602   145001
  xin_cmn    240     1042     2325    43073  1559752
  zbn_cmn     13       44       91     1461    45235
  --------------------------------------------------
             806     4183     9362   181357  5664377

The following tables present "K-wrds" (i.e. thousands of Chinese
characters) and "#DOCS" broken down by source and DOC type:

          #    DOCS   Kwords   TextKB
advis
  afp_cmn        0        0        0
  cna_cmn    10836     1130     4085
  cns_cmn        0        0        0
  gmw_cmn        0        0        0
  pda_cmn        0        0        0
  pla_cmn        0        0        0
  xin_cmn     6971      726     2391
  zbn_cmn        0        0        0
  TOTAL      17807     1856     6476

multi
  afp_cmn        0        0        0
  cna_cmn    43921    33163   109719
  cns_cmn        0        0        0
  gmw_cmn        0        0        0
  pda_cmn        0        0        0
  pla_cmn        0        0        0
  xin_cmn    11428     7518    23818
  zbn_cmn      105      186      596
  TOTAL      55454    40867   134133

other
  afp_cmn        0        0        0
  cna_cmn   134735    51792   197246
  cns_cmn        0        0        0
  gmw_cmn        0        0        0
  pda_cmn        0        0        0
  pla_cmn        0        0        0
  xin_cmn    40851    14068    51463
  zbn_cmn      279      128      443
  TOTAL     175865    65988   249152

story
  afp_cmn   168952    61669   197511
  cna_cmn  2370028  1044144  3300086
  cns_cmn   700082   533901  1697690
  gmw_cmn   122375   115675   365154
  pda_cmn   363460   279432   888147
  pla_cmn   145001   100369   318411
  xin_cmn  1500502   729449  2302353
  zbn_cmn    44851    29692    92456
  TOTAL    5415251  2894331  9161808


GENERAL PROPERTIES OF THE DATA
------------------------------

All of the data sets have been produced from bulk archives that were
delivered to the LDC via internet transfer.  As a result, we avoided
many of the problems that commonly afflict newswire data that has been
transmitted over modems.  Still, some sources contained noticeable
amounts of "noise" (unusable characters, null bytes, etc) which had to
be filtered out for research use.  Two of the corpus authors at the
LDC, Ke Chen and Junbo Kong, are native speakers of Mandarin Chinese,
and did extensive diagnosis to identify and eliminate unsuitable
content in the original archival data.  To some extent, this is an
open-ended problem, and there may be kinds of error conditions that
have gone unnoticed or untreated -- this is true of any large text
collection -- but we have striven to assure that the characters
presented in all files are in fact valid and displayable, and that the
markup is fully SGML compliant.

It is often the case that data from a given source contains duplicate
copies of documents.  In this version of Gigaword, MD5 sums were
generated for the bodies of the newly added documents for each source.
Where mutiple documents had the same MD5 sum, only the first document
(by collection date) was included and the remainder discarded.  This
has the effect of producing a large number of document ID gaps in some
sources.  A file called "removed_duplicates.tab" is included in the
documentation for this package.  The file is tab delimited, where the
first row are headers labeling the columns:

removed_id      -   the document ID that was removed from the corpus
retained_id     -   the document ID that was left in the corpus

Note that for a given retained_id, there may be multiple removed_ids.


The removed duplicates table contains 1118544 rows, with the majority
of the documents from CNS, GMW and PLA.

SOURCE-SPECIFIC PROPERTIES
--------------------------

 - AFP

For this initial release of AFP Chinese news data, the attempt to
classify articles into "story", "multi", "advis" and "other" did not
receive as much attention as was given to other sources in the earlier
releases.  A rapid inspection of the data indicated that AFP does not
publish "tabular" articles (listings of weather, stocks, sports
scores, etc), so the "other" category is essentially non-existent;
also, since the data are conveyed via the web, we do not see the kind
of content that would fall under the "advis" category.  It's likely
that there may be a number of stories that should really be called
"multi" but have not been identified as such.

 - CNA

In second release, there were about 165 empty DOC elements
(having no content with the TEXT tags).  These DOC elements have been
removed.

In prior releases, some CNA files contained full-width equivalents of
characters in the ASCII range. These data were normalized to correct
this issue.

 - CNS

All CNS documents are assumed to be of type story.  LDC collects data 
from CNS' website; owing to flaws in the collection methodology,
a significant number of documents were duplicated in the original data
pool.  558268 duplicate documents were removed from this release.


 - GMW 

All GMW documents are assumed to be of type story. Owing to changes in
GMW's site, LDC reworked our collection of GMW, focusing on just on
the content actually published in the newspaper. The result is a
smaller collection, in terms of volume, with a tigher focus on news
content.

 - PDA

All PDA documents are assumed to be of type story. Note that, due to a
collection problem, approximately the first half of 2009 is absent in
this corpus for this source. There exists a few unicode characters
defined as "Private Use" in the data from this source.

 - PLA

All PLA documents are assumed to be of type story. LDC collects data 
from PLA's website; owing to flaws in the collection methodology,
a significant number of documents were duplicated in the original data
pool.  356668 duplicate documents were removed from this release.


 - XIN

The methodology LDC previously employed to assign types to documents
has ceased to assign any non-story types in this year's data. Owing to
time constraints, we did not investigate alternative methods of doing
the type assignment and will treat all documents in the 2009-2010 data
as being of story type, whatever the "real" type might be.

In preparing the fourth edition, A bug was discovered in LDC's script 
that processes data from Xinhua.  Consequently, a number of document 
received duplicate IDs.  In this publication, documents were 
automatically re-assigned IDs to ensure uniqueness.  The file 
xin_cmn_reassigned_ids.tab is a tab delimited file indciating the 
documents which had their IDs reassigned.  The first line of the file
contains a header row with the field name label:

original_id     -       The original ID that was assigned to the
                        document

replacement_id  -       The automatically generated replacement ID 
                        that was assigned to the document

- ZBN

In prior releases, some ZBN files contained full-width equivalents of
characters in the ASCII range. These data were normalized to correct
this issue.


README file written by David Graff and Ke Chen, January 2003

Updated for the Second Edition by Junbo Kong and Kazuaki Maeda, June 2005.

Updated for the Third Edition by Dave Graff, June 2007.

Updated for the Fourth Edition by Robert Parker, August 2009.

Updated for the Fifth Edition by Robert Parker, October 2011.

Linguistic Data Consortium
