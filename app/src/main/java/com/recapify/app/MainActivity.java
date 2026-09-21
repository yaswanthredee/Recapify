package com.recapify.app;

import android.app.*;
import android.content.*;
import android.graphics.*;
import android.graphics.drawable.GradientDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.*;
import android.view.inputmethod.InputMethodManager;
import android.widget.*;
import java.util.*;

public class MainActivity extends Activity {
    private static final int BG=Color.rgb(7,10,20), CARD=Color.rgb(16,22,38), CARD2=Color.rgb(23,31,52);
    private static final int PURPLE=Color.rgb(132,91,255), CYAN=Color.rgb(42,211,255), GREEN=Color.rgb(64,224,151);
    private static final int ORANGE=Color.rgb(255,177,79), RED=Color.rgb(255,101,125), WHITE=Color.rgb(245,247,255), MUTED=Color.rgb(153,166,195);
    private LinearLayout root,content; private TextView pageTitle,navHome,navCatch,navTasks,navAI,navMore;
    private int dp(int n){return (int)(n*getResources().getDisplayMetrics().density+.5f);}
    @Override public void onCreate(Bundle b){super.onCreate(b);getWindow().setStatusBarColor(BG);getWindow().setNavigationBarColor(BG);buildShell();home();}
    private TextView tv(String s,float z,int c,boolean bold){TextView t=new TextView(this);t.setText(s);t.setTextSize(z);t.setTextColor(c);if(bold)t.setTypeface(Typeface.DEFAULT,Typeface.BOLD);return t;}
    private GradientDrawable bg(int c,int r){GradientDrawable g=new GradientDrawable();g.setColor(c);g.setCornerRadius(dp(r));return g;}
    private GradientDrawable stroke(int fill,int line,int r){GradientDrawable g=bg(fill,r);g.setStroke(dp(1),line);return g;}
    private LinearLayout box(int c){LinearLayout x=new LinearLayout(this);x.setOrientation(LinearLayout.VERTICAL);x.setPadding(dp(16),dp(16),dp(16),dp(16));x.setBackground(bg(c,22));return x;}
    private LinearLayout.LayoutParams lp(int w,int h){return new LinearLayout.LayoutParams(w,h);}
    private LinearLayout.LayoutParams wt(){LinearLayout.LayoutParams p=new LinearLayout.LayoutParams(0,-2,1);p.setMargins(dp(4),0,dp(4),0);return p;}
    private void margin(View v,int l,int t,int r,int b){LinearLayout.LayoutParams p=(LinearLayout.LayoutParams)v.getLayoutParams();if(p==null)p=new LinearLayout.LayoutParams(-1,-2);p.setMargins(dp(l),dp(t),dp(r),dp(b));v.setLayoutParams(p);}
    private TextView button(String s,int color){TextView t=tv(s,14,WHITE,true);t.setGravity(Gravity.CENTER);t.setPadding(dp(14),dp(14),dp(14),dp(14));t.setBackground(bg(color,17));t.setClickable(true);return t;}
    private void buildShell(){
        root=new LinearLayout(this);root.setOrientation(LinearLayout.VERTICAL);root.setPadding(dp(15),dp(8),dp(15),dp(8));root.setBackgroundColor(BG);
        LinearLayout head=new LinearLayout(this);head.setGravity(Gravity.CENTER_VERTICAL);
        TextView logo=tv("✦",26,WHITE,true);logo.setGravity(Gravity.CENTER);logo.setBackground(bg(PURPLE,16));head.addView(logo,lp(dp(46),dp(46)));
        LinearLayout brand=new LinearLayout(this);brand.setOrientation(LinearLayout.VERTICAL);brand.setPadding(dp(11),0,0,0);
        pageTitle=tv("RECAPIFY",21,WHITE,true);brand.addView(pageTitle);brand.addView(tv("AI MEETING INTELLIGENCE",9,CYAN,true));head.addView(brand,lp(0,-2));((LinearLayout.LayoutParams)brand.getLayoutParams()).weight=1;
        TextView bell=tv("♢",23,WHITE,true);bell.setGravity(Gravity.CENTER);bell.setBackground(stroke(CARD2,Color.rgb(47,61,88),15));bell.setOnClickListener(v->notifications());head.addView(bell,lp(dp(44),dp(44)));
        TextView avatar=tv("YR",11,WHITE,true);avatar.setGravity(Gravity.CENTER);avatar.setBackground(bg(CARD2,30));avatar.setOnClickListener(v->profile());head.addView(avatar,lp(dp(44),dp(44)));root.addView(head);
        ScrollView sv=new ScrollView(this);sv.setFillViewport(true);content=new LinearLayout(this);content.setOrientation(LinearLayout.VERTICAL);content.setPadding(0,dp(18),0,dp(20));sv.addView(content);root.addView(sv,lp(-1,0));((LinearLayout.LayoutParams)sv.getLayoutParams()).weight=1;
        LinearLayout nav=new LinearLayout(this);nav.setPadding(dp(3),dp(5),dp(3),dp(5));nav.setBackground(bg(CARD,21));
        navHome=navItem("⌂","Home");navCatch=navItem("✦","Catch Up");navTasks=navItem("✓","Tasks");navAI=navItem("◉","AI");navMore=navItem("⋯","More");
        nav.addView(navHome,wt());nav.addView(navCatch,wt());nav.addView(navTasks,wt());nav.addView(navAI,wt());nav.addView(navMore,wt());
        navHome.setOnClickListener(v->home());navCatch.setOnClickListener(v->catchUp());navTasks.setOnClickListener(v->tasks());navAI.setOnClickListener(v->ai());navMore.setOnClickListener(v->more());root.addView(nav);setContentView(root);
    }
    private TextView navItem(String icon,String label){TextView t=tv(icon+"\n"+label,10,MUTED,true);t.setGravity(Gravity.CENTER);t.setPadding(0,dp(5),0,dp(5));return t;}
    private void clear(String title){content.removeAllViews();pageTitle.setText(title);navHome.setTextColor(MUTED);navCatch.setTextColor(MUTED);navTasks.setTextColor(MUTED);navAI.setTextColor(MUTED);navMore.setTextColor(MUTED);}
    private void active(TextView t){t.setTextColor(CYAN);}
    private void add(View v){content.addView(v);}
    private void add(View v,int top){margin(v,0,top,0,0);add(v);}
    private TextView section(String s){TextView t=tv(s,18,WHITE,true);t.setPadding(0,dp(20),0,dp(9));return t;}
    private LinearLayout stat(String n,String l,int c){LinearLayout b=box(CARD);b.setGravity(Gravity.CENTER);b.addView(tv(n,24,c,true));TextView x=tv(l,10,MUTED,false);x.setGravity(Gravity.CENTER);b.addView(x);return b;}
    private void home(){
        clear("RECAPIFY");active(navHome);
        TextView greet=tv("Good afternoon, Yaswanth 👋",25,WHITE,true);add(greet);
        add(tv("Your meetings, simplified by AI.",13,MUTED,false),3);
        LinearLayout stats=new LinearLayout(this);stats.addView(stat("12","Meetings",CYAN),wt());stats.addView(stat("4.2h","Time saved",GREEN),wt());stats.addView(stat("87%","Focus",PURPLE),wt());add(stats,18);
        LinearLayout hero=box(PURPLE);
        TextView badge=tv("  ✦  SMART CATCH-UP  ",11,WHITE,true);badge.setGravity(Gravity.CENTER);badge.setBackground(bg(Color.argb(50,255,255,255),20));hero.addView(badge,lp(-2,dp(30)));
        hero.addView(tv("Joined late?",25,WHITE,true));hero.addView(tv("Get only what you missed —\nnot the whole meeting.",15,Color.rgb(225,228,250),false));
        TextView cb=button("✦  Generate My Catch-Up",Color.rgb(105,67,225));cb.setOnClickListener(v->catchUp());hero.addView(cb,lp(-1,-2));add(hero,18);
        add(section("Quick actions"));
        LinearLayout qa=new LinearLayout(this);
        TextView a=button("＋ New Meeting",CARD2);a.setOnClickListener(v->pickFile());qa.addView(a,wt());
        TextView b=button("◉ Ask AI",CARD2);b.setOnClickListener(v->ai());qa.addView(b,wt());add(qa);
        LinearLayout qa2=new LinearLayout(this);TextView c=button("✓ My Tasks",CARD2);c.setOnClickListener(v->tasks());qa2.addView(c,wt());TextView d=button("▥ Insights",CARD2);d.setOnClickListener(v->analytics());qa2.addView(d,wt());add(qa2,8);
        add(section("Upcoming"));
        meetingCard("DSA — Graph Algorithms","Today • 2:00 PM • 56 min",CYAN,"LIVE IN 48 MIN");
        add(section("Recent intelligence"));
        recent("Artificial Intelligence — Unit 4","42 min • 4 topics • 2 tasks",PURPLE);
        recent("Software Engineering","35 min • 6 topics • 1 deadline",GREEN);
    }
    private void meetingCard(String name,String meta,int accent,String badge){
        LinearLayout b=box(CARD);LinearLayout row=new LinearLayout(this);row.setGravity(Gravity.CENTER_VERTICAL);
        LinearLayout left=new LinearLayout(this);left.setOrientation(LinearLayout.VERTICAL);left.addView(tv(name,16,WHITE,true));left.addView(tv(meta,12,MUTED,false));row.addView(left,lp(0,-2));((LinearLayout.LayoutParams)left.getLayoutParams()).weight=1;
        TextView tag=tv(badge,9,accent,true);tag.setGravity(Gravity.CENTER);tag.setPadding(dp(8),dp(7),dp(8),dp(7));tag.setBackground(stroke(Color.TRANSPARENT,accent,10));row.addView(tag);b.addView(row);
        b.setOnClickListener(v->meetingDetails(name));add(b,7);
    }
    private void recent(String n,String m,int c){LinearLayout b=box(CARD);b.setOrientation(LinearLayout.HORIZONTAL);TextView icon=tv("✦",20,c,true);icon.setGravity(Gravity.CENTER);icon.setBackground(bg(CARD2,14));b.addView(icon,lp(dp(44),dp(44)));LinearLayout x=new LinearLayout(this);x.setOrientation(LinearLayout.VERTICAL);x.setPadding(dp(12),0,0,0);x.addView(tv(n,15,WHITE,true));x.addView(tv(m,11,MUTED,false));b.addView(x,lp(0,-2));((LinearLayout.LayoutParams)x.getLayoutParams()).weight=1;b.setOnClickListener(v->meetingDetails(n));add(b,7);}
    private void catchUp(){
        clear("CATCH UP");active(navCatch);add(tv("I Joined Late ✨",27,WHITE,true));add(tv("Choose where you joined and Recapify will build a focused recap.",13,MUTED,false),4);
        final TextView mins=tv("20 minutes late",27,CYAN,true);mins.setGravity(Gravity.CENTER);add(mins,20);
        SeekBar sb=new SeekBar(this);sb.setMax(55);sb.setProgress(15);add(sb);sb.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener(){public void onProgressChanged(SeekBar s,int p,boolean f){mins.setText((p+5)+" minutes late");}public void onStartTrackingTouch(SeekBar s){}public void onStopTrackingTouch(SeekBar s){}});
        LinearLayout radar=box(CARD);radar.addView(tv("CATCH-UP RADAR",11,PURPLE,true));
        String[] times={"00:00","06:40","13:20","20:00","28:10","36:00"};String[] topics={"Welcome & agenda","Problem statement","ML concepts","YOU JOINED HERE ↑","Assignment discussion","Q&A and wrap-up"};int[] cs={PURPLE,CYAN,ORANGE,GREEN,MUTED,MUTED};
        for(int i=0;i<times.length;i++){LinearLayout r=new LinearLayout(this);r.setGravity(Gravity.CENTER_VERTICAL);r.setPadding(0,dp(11),0,0);r.addView(tv("●",13,cs[i],true),lp(dp(26),-2));r.addView(tv(times[i],11,MUTED,false),lp(dp(54),-2));TextView tt=tv(topics[i],13,i==3?WHITE:MUTED,i==3);r.addView(tt,lp(0,-2));((LinearLayout.LayoutParams)tt.getLayoutParams()).weight=1;if(i==3)r.addView(tv("MISSED",9,GREEN,true));radar.addView(r);}add(radar,18);
        TextView gen=button("✨  Generate My Catch-Up",PURPLE);gen.setOnClickListener(v->catchResult());add(gen,15);
        TextView upload=button("＋  Upload a meeting / recording",CARD2);upload.setOnClickListener(v->pickFile());add(upload,8);
    }
    private void catchResult(){
        clear("YOUR CATCH-UP");add(tv("You're caught up 🎉",27,WHITE,true));add(tv("A focused recap of the 20 minutes before you joined.",13,MUTED,false),4);
        result("⚡ 30-second summary","The class introduced Machine Learning, explained how models learn from data, and compared supervised and unsupervised learning.",CYAN);
        result("📌 Key points","• Supervised learning → labelled data\n• Unsupervised learning → unlabelled data\n• Classification predicts categories\n• Clustering groups similar data",PURPLE);
        result("📝 Action item detected","Prepare short notes on Machine Learning algorithms.\nDeadline: Friday",ORANGE);
        result("❓ Suggested question","Which algorithm should we use for the assignment?",GREEN);
        TextView ask=button("◉ Ask AI about this meeting",CARD2);ask.setOnClickListener(v->ai());add(ask,4);
    }
    private void result(String h,String body,int c){LinearLayout b=box(CARD);b.addView(tv(h,15,c,true));TextView x=tv(body,13,Color.rgb(218,224,241),false);x.setPadding(0,dp(8),0,0);x.setLineSpacing(0,1.18f);b.addView(x);add(b,9);}
    private void tasks(){
        clear("TASKS");active(navTasks);add(tv("Action Center",27,WHITE,true));add(tv("Everything Recapify detected across your meetings.",13,MUTED,false),4);
        task("Prepare Machine Learning notes","Due Friday","HIGH",ORANGE);task("Complete DAA lab observation","Due tomorrow","URGENT",RED);task("Review project synopsis","This week","NORMAL",CYAN);task("Create Recapify demo","Before presentation","PROJECT",PURPLE);
        TextView add=button("＋  Add personal task",CARD2);add.setOnClickListener(v->taskDialog());this.add(add,5);
    }
    private void task(String name,String due,String badge,int c){LinearLayout b=box(CARD);b.setOrientation(LinearLayout.HORIZONTAL);TextView check=tv("○",23,c,true);check.setGravity(Gravity.CENTER);b.addView(check,lp(dp(38),dp(38)));LinearLayout x=new LinearLayout(this);x.setOrientation(LinearLayout.VERTICAL);x.setPadding(dp(12),0,0,0);x.addView(tv(name,14,WHITE,true));x.addView(tv(due+"  •  "+badge,11,c,true));b.addView(x,lp(0,-2));((LinearLayout.LayoutParams)x.getLayoutParams()).weight=1;check.setOnClickListener(v->{check.setText("✓");check.setTextColor(GREEN);Toast.makeText(this,"Task completed",Toast.LENGTH_SHORT).show();});add(b,8);}
    private void ai(){
        clear("ASK RECAPIFY");active(navAI);add(tv("Recapify AI ✦",27,WHITE,true));add(tv("Ask questions from your meeting knowledge base.",13,MUTED,false),4);
        LinearLayout chips=new LinearLayout(this);String[] q={"What was decided?","Show deadlines","Summarize Unit 4"};for(String s:q){TextView c=button(s,CARD2);c.setTextSize(11);c.setOnClickListener(v->askAnswer((String)((TextView)v).getText()));chips.addView(c,wt());}add(chips,14);
        final LinearLayout chat=box(CARD);chat.addView(tv("✦  Recapify AI",12,CYAN,true));chat.addView(tv("I can answer questions about topics, decisions, action items, deadlines and missed discussions.",14,WHITE,false));add(chat,14);
        final EditText input=new EditText(this);input.setTextColor(WHITE);input.setHintTextColor(MUTED);input.setHint("Ask anything…");input.setSingleLine(false);input.setMinHeight(dp(56));input.setPadding(dp(14),dp(10),dp(14),dp(10));input.setBackground(stroke(CARD2,Color.rgb(48,63,92),17));add(input);
        TextView send=button("Send  ➜",PURPLE);send.setOnClickListener(v->{String s=input.getText().toString().trim();if(s.isEmpty()){Toast.makeText(this,"Type a question",Toast.LENGTH_SHORT).show();return;}chat.addView(tv("You  •  "+s,13,WHITE,true));chat.addView(tv("Recapify  •  Based on your meeting, the main assignment is Machine Learning notes, with a Friday deadline. The discussion also covered supervised vs unsupervised learning.",13,CYAN,false));input.setText("");});add(send,8);
    }
    private void askAnswer(String s){Toast.makeText(this,s+" — see AI response below",Toast.LENGTH_SHORT).show();}
    private void analytics(){clear("INSIGHTS");active(navMore);add(tv("Meeting Intelligence",27,WHITE,true));add(tv("Your productivity snapshot powered by Recapify.",13,MUTED,false),4);
        LinearLayout a=new LinearLayout(this);a.addView(stat("4.2h","Time saved",GREEN),wt());a.addView(stat("87%","Topics captured",CYAN),wt());add(a,18);
        LinearLayout b=new LinearLayout(this);b.addView(stat("14","Key moments",ORANGE),wt());b.addView(stat("7","Questions",PURPLE),wt());add(b,8);
        LinearLayout chart=box(CARD);chart.addView(tv("WEEKLY RECOVERY",11,PURPLE,true));String[] days={"MON","TUE","WED","THU","FRI","SAT","SUN"};int[] vals={42,65,35,78,58,84,70};for(int i=0;i<7;i++){LinearLayout r=new LinearLayout(this);r.setGravity(Gravity.CENTER_VERTICAL);r.addView(tv(days[i],10,MUTED,true),lp(dp(40),-2));ProgressBar p=new ProgressBar(this,null,android.R.attr.progressBarStyleHorizontal);p.setMax(100);p.setProgress(vals[i]);p.setProgressTintList(android.content.res.ColorStateList.valueOf(i==5?CYAN:PURPLE));r.addView(p,lp(0,dp(12)));((LinearLayout.LayoutParams)p.getLayoutParams()).weight=1;r.addView(tv(vals[i]+"%",11,WHITE,true),lp(dp(42),-2));chart.addView(r,lp(-1,dp(25)));}add(chart,18);
        LinearLayout insight=box(CARD2);insight.addView(tv("✦  SMART INSIGHT",11,CYAN,true));insight.addView(tv("You recovered the most time on Saturday. Short catch-ups are keeping your meeting workload manageable.",14,WHITE,false));add(insight,10);
    }
    private void more(){clear("MORE");active(navMore);add(tv("Your workspace",27,WHITE,true));add(tv("Manage Recapify and explore features.",13,MUTED,false),4);
        menu("▥","Meeting Library","Browse your saved meetings",v->library());menu("◉","Analytics","Productivity and recovery insights",v->analytics());menu("⌁","Notifications","Meeting reminders and AI updates",v->notifications());menu("⚙","Settings","Appearance, privacy and preferences",v->settings());menu("ⓘ","About Recapify","AI-powered meeting catch-up",v->about());}
    private void menu(String icon,String h,String sub,View.OnClickListener l){LinearLayout b=box(CARD);b.setOrientation(LinearLayout.HORIZONTAL);TextView i=tv(icon,21,CYAN,true);i.setGravity(Gravity.CENTER);i.setBackground(bg(CARD2,14));b.addView(i,lp(dp(44),dp(44)));LinearLayout x=new LinearLayout(this);x.setOrientation(LinearLayout.VERTICAL);x.setPadding(dp(12),0,0,0);x.addView(tv(h,15,WHITE,true));x.addView(tv(sub,11,MUTED,false));b.addView(x,lp(0,-2));((LinearLayout.LayoutParams)x.getLayoutParams()).weight=1;b.setOnClickListener(l);add(b,8);}
    private void library(){clear("MEETING LIBRARY");add(tv("12 meetings",27,WHITE,true));add(tv("Search and revisit your meeting intelligence.",13,MUTED,false),4);meetingCard("Artificial Intelligence — Unit 4","Today • 42 min • 4 topics",PURPLE,"ANALYZED");meetingCard("Software Engineering","Yesterday • 35 min • 6 topics",GREEN,"ANALYZED");meetingCard("Data Analytics","Sep 18 • 48 min • 8 topics",CYAN,"ANALYZED");}
    private void meetingDetails(String name){new AlertDialog.Builder(this).setTitle(name).setMessage("AI analysis ready\n\n4 topics captured\n2 action items\n1 deadline\n3 questions detected\n\nOpen Catch Up to see a focused recap or Ask AI to explore the meeting.").setPositiveButton("Catch Up", (d,w)->catchUp()).setNeutralButton("Ask AI",(d,w)->ai()).setNegativeButton("Close",null).show();}
    private void notifications(){new AlertDialog.Builder(this).setTitle("Notifications").setMessage("✦ Your DSA meeting starts in 48 minutes.\n\n✓ Recap generated for Artificial Intelligence.\n\n⚡ 2 action items are due tomorrow.").setPositiveButton("OK",null).show();}
    private void profile(){new AlertDialog.Builder(this).setTitle("Yaswanth Reddy").setMessage("Student workspace\n\n12 meetings analyzed\n4.2 hours recovered\n8 action items tracked").setPositiveButton("Close",null).show();}
    private void settings(){new AlertDialog.Builder(this).setTitle("Settings").setSingleChoiceItems(new String[]{"Dark mode (recommended)","Compact view","High contrast"},0,(d,w)->d.dismiss()).setNegativeButton("Close",null).show();}
    private void about(){new AlertDialog.Builder(this).setTitle("Recapify ✦").setMessage("AI Meeting Intelligence\n\nCatch up faster. Remember better.\n\nBuilt as a student project with a focus on practical AI productivity features.").setPositiveButton("Nice!",null).show();}
    private void taskDialog(){final EditText e=new EditText(this);e.setHint("Task name");e.setPadding(dp(20),dp(10),dp(20),dp(10));new AlertDialog.Builder(this).setTitle("Add task").setView(e).setPositiveButton("Add",(d,w)->{if(e.getText().length()>0)Toast.makeText(this,"Task added: "+e.getText(),Toast.LENGTH_SHORT).show();}).setNegativeButton("Cancel",null).show();}
    private void pickFile(){Intent i=new Intent(Intent.ACTION_OPEN_DOCUMENT);i.addCategory(Intent.CATEGORY_OPENABLE);i.setType("*/*");startActivityForResult(i,99);}
    @Override protected void onActivityResult(int r,int c,Intent d){super.onActivityResult(r,c,d);if(r==99&&c==RESULT_OK&&d!=null){Toast.makeText(this,"Meeting imported successfully",Toast.LENGTH_LONG).show();catchUp();}}
}
