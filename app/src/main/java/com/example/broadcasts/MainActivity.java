package com.example.broadcasts;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
/*
la7 neke l youm 3an 3onsour 3 mn 3aner l android l 2asaeye le heye l broadcast

badna na3ref sho ya3ne broadcast?sho ya3ne broadcast reciever ? ween momken nest5dmo ? sho l feyde meno?

mnerja3 mnetzakar eno fee 4 commponents asaseye bel android:
Activity le ne7na 5alasneha
servies ne7na 5alasneha
broadcasts recievers l youm la7 ne7ke 3ano
content provider ba3d l broadcast reciever


tab3an lama ne7ke 3an l broadcast ne7na mne7ke 3an mostala7 ma3neto bel 3arabe l (البث) sho ya3ne 5alena nshof example
bshakl 3am ne7na mna3ref eno 3amlyet l etesal sawe2 3mlyet etesal bel internet 2aw mobile bte3temed 3ala ersel w este2bel bel 2esharat
hala2 law neftered eno 3ana bourj etesalet heda l bourj bya3mel بث la eshara hay l eshara lama l bourj ya3mel ela بث  ne7na mn sameha broadcast heda l broadcast le bya3mlo l bourj btem la khaled 2aw la ahmad 2aw la aye sha5s mn l ah5as tab3an la2
howe l بث btem bshakl 3am w sha5s le bado yest2bel heda بث  byest2blo
exmple tene ne7na  hala2 bel akmar sena3eye 2aw bshakl 3am be ma7atat radio lama nkoun ne7na meshyen b sayara 2aw b telephona badna nest2bel esharet radio hal l kamar sena3e be  بث l eshara ele sha5seyan byersela la telephone 2aw la sayrte bas b7ad zeta  tab3an la2
l 2amar sena3e   b يث l eshara 3ama w le bye7tej le bado ya3ne byest2bel l eshara b7ot 3ando mostkabel esharat w bekou 3ando asln most2bel  3ashen yest2bel hay l eshara fa hay feret l broadcast 2aw l بث bshakl 3am eno fe 3ande m7atet ersel btersel eshara la kel l 3alam kel denye la kel l 7awale la heda l bourj w sha5s l most2bel be7ob yest2bel hay l eshara bekoun 3ando jehez most2bel 3ashn yest2bel hay l eshara ya3ne l bourj heda 2arsal eshara la kel le 7awale  sha5s heda 3ando jehez este2bel 2eder yest2bel l eshara msln sha5s tene le 7ado ma3ndo jehez este2bel fa ma 2eder sta2bal l eshara..etc
broadcast: howe mostal7 ma3neto nshr 2aw l بث  eno ana bade بث eshara la kel l 7awalaye
broadcast reciver:ya3ne most2bel l بث aw most2bel l eshara heda byest2bel hyde l broadcast bene2an 3ala reciver 2aw most2bel mawjoud 3ano ya3ne bdef she 3ando mn 5elela bye2dar yest2bel l eshara le bado yeha
heda l broadcast bshakl 3am

hala2 sho howe l broadcast le bel android development:
7akelak enta a7yenan bseer 3andak events a7des bel system 2aw bel applications bte7tej enak tbale8 feha be2e l applications le mawjoude 3al device 2aw bel system ya3ne keef ya3ne:
ana  msln mojarad ma 7ot telephone 3ala charge ba3te eshara la kel l applications eno telephone hala2 ma7tot 3ala charge tyeb lesh ba3mel l 7arake hay lesh system taba3 l android bya3mel l 7arake hay l2no momken enta te7tej ba3d l applications le mawjoude 3andak bel system ena ta3mel she m3ayan 7adas m3yan wa2t telephone yen7at 3al charge metl l backup bel watsap wa2t ykoun telephone ma fe charge ma byam3mel backup 2ama wa2t telephone yen7at 3al charge betle2e la7alo 3emel backup fa l system houn ba3at eshara la kel l application eno telephone n7at 3ala charge fa l watsp sta2bala w 3mel 7adas m3yan bene2an 3ala eno telephone n7at 3ala charge
mesel tene tawsel sama3at fe applications msln mojarad ma enta tosel sama3at bel telephone btsha8elak music metl radio taba3 telephone ma byeshte8l ela iza keen ma7tot sama3at

tyeb l applications nafso keef 3eref eno telephone n7at 3ala charge 2aw sama3at nwasalo 3al telephone?
mn 5elel eshara  2arsala system la kel l applications le mawjoude 3ala telephone hay l eshara btetdaman eno telephone n7at 3ala charge  w 2aloun lal application eno hay eshara ba3telkoun yeha btetdaman eno telephone n7at 3ala charge fa l application le bye7tej ya3mel 7adas m3yan bas yen7at telephone 3ala charge byoktoub code m3ayn bado ye yetnafaz w bya3mel 3ando most2bel receiver  w benfez l code le bado ye yetnfaz bel most2bel le mawjoud 3ando hay fekret l بث bshakl 3am bel android eno fe 3ande system ببث aw byshonshor ma3lome mo3yane mno3 mo3ayn la kel l applications le mawjoude 3al telephone w bye7keloun ya applications ana sar fe 3ande 3amlye mo3yane l 3malye hay iza btelzamkounn sta2bloha sar fe 3ande event mo3yan action mo3yan mno3 action mo3yan iza byelzamkoun st2bloha

tab3an fe event kteer momken tseer b system metl tawsel l telephone 3al charge,tawsel sama3at,ta3mel 3amlyet etesal call ...ect fe events ktera


hala2 3ande system b2lbo fe dowyra btmsel broadcast bya3mlo system heye mawjoude bde5el system btmsel 3amlyet l broadcast 3mlyet l بث ya3ne system lama ye7tej yoshor eshara mo3yane byonshora 2aw ببث la kel l applications le 3ando fa fe3lyan byersel l eshara la kel l applications w byersel ma3a intent l intent hay metl ma ne7na 3arfanen mn zamen class bye7mel data bede5lo bte7mel ma3lomet 7awl l action le sar 2aw 7awl l event le sar ya3ne msln eno telephone n7at 3al charge..etc fa byersel intent feha ma3lomet eno telephne n7at 3al charge eno hal tam 7at telephone 3al charge 2aw la2 w kamen bel nesebe la esmple l call byeb3at broadcast la kel l application byeb3at intent ma3o feha ma3lomet eno sho nou3 l etesal le enta bta3mlo byeb3at l status tab3 l etesal hal fasalet l etesal hal ba3do l etsal sha8al fa fe3lyan system byersel eshara la kel l applications mnsameha hay l eshara broadcast byonshora la kel l applications w byersel ma3 l broadcast intent bte7mel bayanet l eshara hay
wala law keen fe applications m3ayan 7ebeb yest2bel eshara mno3 mo3ayn be3ref de5lo la heda l application recever mostakbel metl ma 7kena 2abl shway le bado yest2bel l eshara ezm ykoun 3ando receiver eno la yest2bel l eshara fa aye application bado yest2bel l eshara lezm ykoun 3ando most2bel ye2dar mn 5elelo yest2bel l eshara fa b3ref she bde5el l applicatio le bado yest2bel l eshara she esmo broadcast reciever of type x sho ya3ne
ya3ne kel boradcast reciver lama t3arfo lezm ykoun elo action iza mntzakar mn zamen lama kona net3emal ma3 l intent filter w l intent kona n3ref intent filter w n7ot joweta action lesh 3ashn lama bade sha8el activity mo3yane 2aw bade 2a3mel 3mlye mo3yane ble2e l activity le btsha8ela msln lama kont 2a3ml share la sha8le mn application la application kont 3aref  l activity le bada tefta7 soura a3teha action pic 2aw action send lal mosharake 2aw aye action mn l actions  2aw action call fa kont 3aref action la kel 3amlye bade yeha lesh 3ashn 2e2dar efta7 l application le bya3mel heda l action
nafs she houn kamen l broad cast reciver lezm 3areflo nou3  msln naw3o 3mlyet etesal call fa aye broadcast mn l system btem noaw3o call etesal byest2blo l application heda le m3raf fe l broad cast reciver of type call lesh byest2blo l2no howe m3ref reciver bede5lo naw3o nou3 etesal
exmple tene  ana 3ande broadcast naw3o 3mlyet etesal w reciver le m3rfo mno3 etesal esmo call msln w system arsal broad cast mno3 device is charge hal l application heda 7a yest2blo la2 l2no mano m3ref reciver mno3 device is charge m3ref howe noo3 call fa ma 7a yest2blo sa7 eno l application ta3ele 3ando reciver bas naw3o mesh device is charge fa ma 7a ye2dar heda reciver yestbel l broadcast ya3ne now3 reciver ma byetwefa2 ma3 no3 l borad cast le 2arsalo system fa aymta best2bel heda l broad cast iza keen 3ande reciver w bnafs l wa2t ykoun naw3o nafs no3 l broadcast le ejene mn system
fa bel mo5tasar aye application be7tej eno est2bel eshara mo3yane ba3ref b2lbo broadcast mnafs no3 le bade yeh  fa iza system ba3tel hay l eshara mn heda no3 7a ye2dar l appication yest2bela
ya3ne fe3layn rabet baynetoun howe nou3 fa l application ta3ele beshof eno fe broad cast bas iza ma keen nafs nou3 ma byest2bela


hala2 mola7za jdede:
eno fe broad cast momken byejene mn system mn l anwa3 le 7kena fehoun metl device is charge ...etc msln lama 2osel device 3al charge fe3lyan system bya3ref eno nwasal charge 3al telephone bas l application ta3ele ma bya3ref ela eza 3mel reciver mno3 telephone nwasal 3al charge fa fe3layn system howe le byersel broadcast lal application

bas alak 3mlnelak no3 tene eno momken l application ta3ele yersel l broadcast bas ma bye2der yersel la application tene mobshratan la2
application ta3ele byersel l boradcast la system b2olo ya system ana bade 2a3ml broadcast mno3 x fa aye application tene m3ref 7alo eno 3ando reciver byest2bel broad cast mno3 x bye2dar yes2bel l action heda
w bas neb3at mn l application ta3ele la system broad cast beb3t kamen ma3o intent feha l ma3loet bye5od system hyde l broadcast ma3  intent byersela la be2e l application w l application le m3ref b2lbo reciver mnafs no3 byestbel heda l boradcast

fa hay feret l broadcast eno 2e2dar etresal mn system lal application 2aw been applications ma3 ba3d

fa ana bas est3mel l broadcast ya best5dmo ka morsel broadcast 3ala l system w system yerslo la be2e l application
2aw best5dmo lal broadcast ykoun ka mostkbel reciver la yest2bel action mn system

fa lezm 2a3mel 2awl she n7ot belna tasel lal broad cast metl ma tafsna eno aye 3mlye ba3mela 2aw aye commponent best3mlo mn l 4 activity,servies,broadcast,contentprovider le ba3refoun lezm ykoun m3raf bel manifist file

fa fe 2 type la 3aref bel manifist l broad cast:
ya b3ref ebel manifixt w bsajel l 3osnour fa bseer l broadcast fa3al mojarad ma nazel l application ya3ne 7ata law l application mskar w shabakt telephone 3ala l charge byetnafaz l code le mawjoud bel reciver le ana 3emlo bel application reciver 3ebra 3an class 7a nshofo

2aw eno sajel l broadcast bel context nafso ya3ne bel activity m3yane ya3ne sajel l broadcat demn netak mo3ayan 7a nshof heda  kalem kamen


hala2 badna ntbe2 l kalem :

hala2 metl ma tf2na eno 3ande ya broadcast best5dmo ka morsel 2aw broad cast best5dmo ka most2bel

fa hala2 2awl immplentation bade est2bel action mo3yan esmo boot commpleted ya3ne bade bas erja3 3eed tash8el l telephone ytale3le toast 2aw kel ma y3eed tsha8el jawel ytle3le shshe y2ole et5el l password  yaa3ne bade 2a3ml aye sha8le kel ma 3eed tash8el l telephone

fa beje 2awl she 7adas e3adet tash8el l device 2aw tash8el l telephone howe action mot3le2 bel system ya3ne system le bya3mlo emtl action device n7at 3al charge,2aw sama3t 2aw na3mel call 2aw blototh kel haw 5asoun bel system l2no btseer bel system
fa hal2 bade 3aref mostkbel reciver 3ashn 2e2dar 2est2bel l broadcast le bado yeb3tle yeh system 3end 2e3adet tash8el device
fa beje b3ref mostkbel keef b3rfo:
ba3mel new-other-broadcastreciver
broadcastreciver:howe 3ebara 3an mostkbel class bade same bootreciver fa 3ande class hal2 extend class esmo BroadcastReciver
heda l class b2lbo method wa7de esma onrecive 3anda 2 parameter l context w l intent le bada tejene mn l broadcast taba3 system
b2lab hay l method bektob l code le bado yetnafaz 3end este2bel l broadcast byersel l context le sha8l l reciver,w l intent nafsa system byersela lal recviver 3ashn te5od mena l bayent
fa bkel basata momken eje 2a3mel toast   boot completed momken badel hay toast 2a3eml aye 3mlye bade yeha sha8el servies sha8el activity etba3 she l bade yeh
fa hala2 hay l onrecive() :heye call back la7ala btestd3a aymta btestd3a la7 testd3a hay l method b mojarad ma ykoun no3 l broadcast mnafs no3 reciver
tyeb hala2 l broadcast t3araf bel manifist  tyeb hala2 hal heda l reciver 7a ystd3a 3end ekmel 3amlyet tahs8el 2aw 3end shabk same3a 2aw charge..etc lesa enta mesh m7aded meen le b7aded naw3a le howe l action metl ma koun net3emal ma3 l activity keen 3ande intent filter b2laba action fa fe3layn le b7aded aymta yeshte8l l reciver howe l action le bekoun le bekoun m3araf b2lab tag taba3 l reciver keef b3ref:
bje ba3mel intent filter jowa intent filter b3ref action metl fekret l action ta3et l activity tab3an anwa3 kteer:
msln no3 l boot howe:Boot_Completed w fe ad ma badak action airplane mode,charge,reboot....etc
 <intent-filter>
                <action android:name="android.intent.action.BOOT_COMPLETED"/>
            </intent-filter>
fa hal2 sorna m3rfen eno l action heda 3end ektemel 3amlyet l boot 2aw 3edn tash8el fa ahal2 5als sar m3raf betre2a hay ya3ne sorna eylen eno hayda l application byest2bel reciver mno3 2e3det tah8el l device


note:ne7na 2olna l action ya mn7oto bel manifist 2aw byetsajal 3ala context m3yan
feek ya3ne b context:
ya3ne b activity mo3yane msln bel mainactivity tyeb sho btefre2:le byefre2 eno lama tsajlo b mafist bekoun 3am 3ala kel l application 2awal ma yseer intall lal application bedal heda reciver sa7e la aye 7adas mno3 l action le 7adadto  ba3den la2o eno heda she byesthlek men l battary fa 2alak lesh ydal heda mote7 ana yemken ma be7tejo ela b maken m7dada mesh bkel l application
2alak ne7na ba3d l android 8 wa2fna hay l 3amleye eno tsajel bel manifist l afdal l2afdal tsajlo b cotext b activity
keef ba3mlo bel activity nafs ma 3mlt bel manifist:
beje b3ref l reciver sho esmo bootreciver :Bootreciver bootreciver=new Boootreciver()
tene sha8le b3ref l intentfilter lesh 3ashn 3aref bede5lo l action:IntentFilter filter=new IntentFilter(Intent.ActionBoot_Completed) w b7ot b2lbo l action le bade yeh

ba3d ma 3mlt haw best5em method esma registerReceiver() w bte5od 2 arguments le howe l 2awl l reciver taba3e tene howe l intent filter heek enta sajlt  reciver b2lb hay l activity hay l context fa le 7a yseer ba3d ma t3eed tash8el l device ba3d ma tsajel l reciver bel system mafroud eno system yeb3at broadcast la kel l application eno tam 3mlyet e3adet tash8el fa l application le ana 3emlo byest2bel heda l action 3ando reciver byest2bel action mno3 e3det tash8el device fa benfez l code le bede5l l onrecive

fe method teneye esma unregisterreciver():hyde btel8e l regestartion ta3el l reciver feek hay ta3mela bel ondestory bte5od l reciver le 3mlto


w fe mola7za moheme eno lezm test5dem permission esmo recive_boot_completed heda btest5dmo bas iza badak ta3mel 7adas mno3 boot_completed

exmple 2 bade bas 7ot sama3at 2aw 7ot charge 3al telephone ysha8ele music 2aw yetb3le taost 2awl she bade jareb

ma3lome mohme eno l eshay le bteshte8l bel ui metl toast w music bada context fa hawde ma byeshte8lo ela iza 3arft l reciver bel activity w ma byeshte8lo iza ma kenet fete7 l activity

example tene bade eshbek sama3a bas eshbeka etba3 toast eno plugged bas efsela toast l earphone off keef:
2awl she l action plug_headset bas l meshkle eno hayda 3ebra 3an action byostd3a l onrecive iza fasl sama3a wala 7at same3a fa keef mn7ola
bel onrecive howe byeb3tlk intent feha m3lomet 3an l action fa b2lab hay l intent fe extra met3l2a bel state taba3 same3a eno hal heye plugd in or no


note reciver taba3e momken 3arfo 3ala actar mn action ya3ne 2olo badak teshte8l 3ala action charge,w call,w ..etc 3ade
w keef bt3ref howe aye action bado ynafez bel onrecive btjeeb l action mn l intent bt2olo if intent.getaction.equlls("") btjeeb esm l action w bt2olo rou7 nafezle l code heda

note:hala2 fe ma3lome moheme lezm nefhama ma3 ba3d bas net3emal ma3 l broadcast reciver w l 8aleb byo2a3 b nafs l meshkle:
l fekra eno method l onrecive feha code mo3ayan heda l code toast mo3ayan baseta
l method onrecive aymta btestd3a lama yseer l broad cast action l broad cast
lama l method testd3a 5elel tanfez l code le joweta btem ta3amoul ma3o l code le joweta eno howe 3ebra 3an foreground process 2aw foreground service keef ya3ne
l code asne2 tash8elo bel onrecive toool ma l code sha8al b2laba w lisa ma 5elel exuction lal code tool ma system byet3emal ma3a 3ala eno foreground service ya3ne hay ela 2awlaweye 3aleye bel baka2 ma byetfeha system lama ne7tej memory ela fe aswa2 l 7alet lama tsaker ma3o la system neh2yen ma yle2e she tene ysakro 2a2al 2awlaweye eno ysakera fa besker hay ya3ne 2awlaweyeta bel baka2 3alye
asne2 tashe8l l onrecive w tash8el l code bede5ela 2awlaweyeta bel baka2 3alye
2alak deymn akeeed 7a ynafez l code le joweta w y5les  tyeb ba3d ma y5les kel excution taba3 l code sho bseer byet3mela ma3a bseer b2awlaweye 2a2al fa system fe3layn law 7tej memory momken ysakera
tyeb sho 7a yefre2 ma3e heda l kalem ma ana deymn asln mesh 7a etl3 mn l method hay ela w m5les le feha alak la2 momken l code le mawjoud feha  ye5od wa2t tawel metl l thread .sleep tyeb mesh hay msln bade sha8ela la modet 10 sawene mesh bte5od wa2t tawel 2aw l music keloun bye5do wa2t tawel 2alak fe3layn law l code le b2laba tawal zyede 3an lozoum l broadcarst reciver bya3mel return law7do bala ma y2olak ya3ne bye3teber eno 5alas l method le jowet l onrecive
fa ana mesh l mafroud mene 2a3ml code bye5od wa2t tawel  bel onrecive tyeb sho mnsewe bel 7ale hay
bt3mel thread jdede bte7kelo new thread 2aw async task..etc fa fe3layn enta heek nafzt code modeto 10 sawene b thread monfesle enta heek 7alet meshkelat sa7?
alak sa7 eno enta heek 7alet l meshkle bas fata7t meshkle jdede sho heye?
nerja3 lal ma3lome le 2olneha abl shway l onrecive tol ma heye sha8ale tool ma l code byeshte8el ka2no foregroud process tyeb ana fout 3al method hay lama sar l brodcast sat2blta w sha8lt thread ba3den w ra7 3emel return do8re eno howe 5als l method fa howe fe3layn 5als l method abl ma thread t5les sho8la ya3ne l thread saret sha8ale w momkn system yel8eha fe aye wa2t 3ashn heek hay tare2a 8alat eno nta trou7 tsha8el thread w ba3den tetla3 mn l onrecive enta heek fe3layn sha8alt thread fa 7a yrou7 ya3mel retun w ysaker l method mesh 7a yestnek enak t5les sho8lak l2no enta sha8al hala2 b thread monfesle fa le sar eno tele3 mn l onrecive 3emel return abl ma y5les sho8l l thhread fa batal system yet3emal ma3 l broad cast eno foreground process sar yet3emal ma3a b 2awlaweye 2a2al w momken b aye wa2t ye7tej momery ysaker l thread hay w yel8eha
alak tyeb sho l7al?
fe method 3ana esma goasync:
hay method bt2olak lama tkoun bt3ref 7alak badak tst5dem code bye5od wa2t tawel metl l async task,thread,music 2alak st3mel goasync()
fa method l goasync btrej3 she esmo pending result fa ba3mel PendingResult result=goasync();
fa hala2 k2ne bel zabt wa2t estd3e l goasync() k2no b2olo 5ale belak bade eshte8l sho8l bye5od wa2t tawel fa 5alek sa7e ma3e ma ta3mel retrun de8re fa bas 5les sha8le le bye5od wa2t b2olo result.finish() ya3ne ana 5alst she8le sar feek twa2efa ya3ne b2olo 5als wa2ef l goasync() ana 5alst kel she8le fa hay l fekra lama et3emal ma3 l onrecive lama ykoun fe 3ande code bye5od wa2t tawel
mnerja3 mn2ol sho mna3mel brou7 b3ref goasync() l2no ba3ref 7ale bade eshte8l code bye5od wa2t tawel w momken heda l code ye5od wa2t la ba3d ma t5les l onrecive w ta3mel return bdalo l code le bye5od wa2t w bekoun ma 5als fa l onrecive 7a t5les msln abl ma y5les l thread.sleep fa ana b2olo ma tsakera lal onrecive 5alek 2e3ed nater la ye5las she8l l code ba3den sakera

hal2 btabe2 mesl l music bade est5dem servies lesh l2no music bade sha8lo fa a7san sha8lo b servies l2no bye5od wa2t tawel fa l a7san sha8lo b servies




w 2e5er she fe 3ana method esma unregesteredreciver(reciver) hyde msln 2aw2at mn3oza iza badna nl8e l reciver bas netla3 mn l app bel ondestory 3ashn ywafer battary bhay tare2a b7afez 3ala l device w l battary

hala2 ana momken ykoun 3ande 3 broadcast reciver metl ma 3mlna wa7de lal earphone wa7de iza tafa telephone wa7de iza charge hawde malsn iza saro ma3 ba3d sa3eta la7 ya3mlo execution koloun tyeb barke ana bade rateboun msln 2awl wa7ad ya3mel execution howe ta3el earphone ba3den l charge ba3de msln bas yerja3 yetfe telephone keef bade 2a3mela:
w ana be2dar kamen eb3at data mn broadcastreciver la broadcast reciver tene berja3 eb3at data mn broadcast tene la broadcast telet
la na3mel heda she kolo badna nest3mel she esmo ordered Broadcast recivers

hala2 abl ma na3mel implentation badna nshof hay l mechanism keeef bteshte8l:


3ana 2 recevers m3arafen bel manafist sar l action ma ba3d nba3at l intent hala2 keef badoun yetnafazo w keef bade 7aded meen yeshte8l 2awal
3ana she esmo bread crumb structure howe 3ebara 3an nafs l mawjoud bel android studio ta7t btle2e eno keef l file mnazameen fo2 ba3d example/broadcast/mainactivity
ne7na la7 nest5dem nafs she la na3mel detremine aye broad cast belesh w aye broad cast e5er wa7ad metl hhed start-broadcast reciver 1-broadcast reciver 2
2awl tare2a heye fe tag b2lab reciver esma pririty fene 7aded mn 5elela eno hay reciver number 1 hay reciver numbr 2

w fe tare2a tenye esma serorderbroadcats bas 2abl ma e7ke 3lyha badna neshof keef fena ne7na neb3at broadcast lal be2e l application ne7na shofna keeef fena nest2bel bas ma shofna keef fena neb3at

2awl she badna na3mel reciver b2lbo action mn7ot aye strin b2lbo le badna yeh w l category lezm n7ota defult
mnerja3 bel main activity mna3mel inetnt mn7ot b2laba action nafs l action ta3et l manifist nafs l string w category deffult kamen
ba3den 3ande method esma sendbroadcast() bte5od intent hay l method bteb3at broad cast lal system byets2bela byerja3 system byeb3ata lal application le 3ando reciver 3ala heda l braodcast byest2bela

fa ne7na mna3mel broadcastreciver ba3den b xml tag mn7aded l actio aye name mnerja3 mna3mel intent bel main w mn7aded l action ba3den mna3mel sendbroadcast
hala2 bel application tene mna3mel naf she bas bala ma na3ml immplment lala onrecive fa bas nekbous l button ta3el awal application byeb3at l broad cast byest2bela tene application w mnfez l onrecive le mawjoud b 2awl application
hala2 ana barke bade yeh heda l broadcast bas yest2blo l application ta3ele heye l broadcast le abl byest2blo kamen l application ta3ele 3ade maw howe system byeb3to la kel l application fa 7a yest2blo bas ana bas bade l application ta3le
sa3eta best3mel localBroadcat.getisntance(this).sendbroadcast()         localBroadcastManager.registerReceiver(myReceiver,intentFilter);                 localBroadcastManager.sendBroadcast(intent);
 haye byemshe 7ala 7ata law m3ref l broadcast eno exported bye2dar yeb3at lal application tenyen
ama fe tare2a tenye kamen eno bas t7ot l exported false w ma tektob she tene


hala2 mnerja3 la mawdo3 l order: 2olna 2awl tare2a heye l manifist attribute esma order hay le 7a nest3mela
finsihing broadcast


 */




public class MainActivity extends AppCompatActivity {
    ActivityResultLauncher<String> activityResultLauncher;
    Button button;
    MyReceiver myReceiver;
    LocalBroadcastManager localBroadcastManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        localBroadcastManager=LocalBroadcastManager.getInstance(getApplicationContext());
        myReceiver=new MyReceiver();
        IntentFilter intentFilter=new IntentFilter("com.example.broadcasts");
        localBroadcastManager.registerReceiver(myReceiver,intentFilter);
        button=findViewById(R.id.button);
        activityResultLauncher=registerForActivityResult(new ActivityResultContracts.RequestPermission(), new ActivityResultCallback<Boolean>() {
            @Override
            public void onActivityResult(Boolean o) {

            }
        });
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.UPSIDE_DOWN_CAKE) {
                activityResultLauncher.launch(Manifest.permission.POST_NOTIFICATIONS);
            } else {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
                    ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.POST_NOTIFICATIONS}, 1);
                }
            }
        }

        BootReceiver bootReceiver=new BootReceiver();
        EarphoneReceiver earphoneReceiver=new EarphoneReceiver();
        chargeReceiver chargeReceiver=new chargeReceiver();
        IntentFilter filter=new IntentFilter(Intent.ACTION_HEADSET_PLUG);
        registerReceiver(earphoneReceiver,filter);



            button.setOnClickListener(v->{
                Intent intent=new Intent();
                intent.setAction("com.example.broadcasts");
//                sendBroadcast(intent);
                localBroadcastManager.sendBroadcast(intent);
            });








    }
}