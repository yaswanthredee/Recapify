package com.recapify.app;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.GradientDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.SeekBar;
import android.widget.Space;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

    private static final int BG = Color.rgb(6, 10, 20);
    private static final int CARD = Color.rgb(15, 22, 38);
    private static final int CARD2 = Color.rgb(20, 29, 50);
    private static final int PURPLE = Color.rgb(124, 92, 255);
    private static final int CYAN = Color.rgb(35, 213, 255);
    private static final int WHITE = Color.rgb(244, 247, 255);
    private static final int MUTED = Color.rgb(158, 171, 199);
    private static final int GREEN = Color.rgb(67, 222, 148);
    private static final int ORANGE = Color.rgb(255, 177, 84);

    private LinearLayout root;
    private LinearLayout content;
    private TextView title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Window w = getWindow();
        w.setStatusBarColor(BG);
        w.setNavigationBarColor(BG);
        showShell();
        showHome();
    }

    private void showShell() {
        root = new LinearLayout(this);
        root.setOrientation(LinearLayout.VERTICAL);
        root.setBackgroundColor(BG);
        root.setPadding(dp(16), dp(10), dp(16), dp(8));

        LinearLayout top = new LinearLayout(this);
        top.setOrientation(LinearLayout.HORIZONTAL);
        top.setGravity(Gravity.CENTER_VERTICAL);

        TextView logo = text("✦", 28, CYAN, true);
        logo.setGravity(Gravity.CENTER);
        logo.setBackground(round(PURPLE, 18));
        LinearLayout.LayoutParams lpLogo = new LinearLayout.LayoutParams(dp(48), dp(48));
        top.addView(logo, lpLogo);

        LinearLayout nameWrap = new LinearLayout(this);
        nameWrap.setOrientation(LinearLayout.VERTICAL);
        nameWrap.setPadding(dp(12), 0, 0, 0);
        title = text("RECAPIFY", 22, WHITE, true);
        TextView tag = text("Never Miss What Matters", 12, MUTED, false);
        nameWrap.addView(title);
        nameWrap.addView(tag);
        top.addView(nameWrap, new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT, 1));

        TextView profile = text("YR", 13, WHITE, true);
        profile.setGravity(Gravity.CENTER);
        profile.setBackground(round(CARD2, 40));
        top.addView(profile, new LinearLayout.LayoutParams(dp(42), dp(42)));
        root.addView(top);

        ScrollView scroll = new ScrollView(this);
        scroll.setFillViewport(true);
        content = new LinearLayout(this);
        content.setOrientation(LinearLayout.VERTICAL);
        content.setPadding(0, dp(18), 0, dp(18));
        scroll.addView(content, new ScrollView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        root.addView(scroll, new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 0, 1));

        root.addView(navBar());
        setContentView(root);
    }

    private LinearLayout navBar() {
        LinearLayout nav = new LinearLayout(this);
        nav.setOrientation(LinearLayout.HORIZONTAL);
        nav.setPadding(dp(4), dp(6), dp(4), dp(6));
        nav.setBackground(round(CARD, 22));
        nav.addView(navItem("⌂\nHome", new View.OnClickListener(){ public void onClick(View v){ showHome(); }}), weight());
        nav.addView(navItem("✦\nCatch Up", new View.OnClickListener(){ public void onClick(View v){ showCatchUp(); }}), weight());
        nav.addView(navItem("✓\nTasks", new View.OnClickListener(){ public void onClick(View v){ showTasks(); }}), weight());
        nav.addView(navItem("◉\nAI", new View.OnClickListener(){ public void onClick(View v){ showAI(); }}), weight());
        nav.addView(navItem("▥\nAnalytics", new View.OnClickListener(){ public void onClick(View v){ showAnalytics(); }}), weight());
        return nav;
    }

    private LinearLayout.LayoutParams weight() {
        return new LinearLayout.LayoutParams(0, dp(58), 1);
    }

    private TextView navItem(String s, View.OnClickListener l) {
        TextView t = text(s, 10, MUTED, false);
        t.setGravity(Gravity.CENTER);
        t.setOnClickListener(l);
        return t;
    }

    private void clear(String screenTitle) {
        content.removeAllViews();
        title.setText(screenTitle);
    }

    private void showHome() {
        clear("RECAPIFY");
        content.addView(text("Good evening, Yaswanth 👋", 25, WHITE, true));
        TextView sub = text("Ready to catch up without missing the flow?", 14, MUTED, false);
        sub.setPadding(0, dp(5), 0, dp(18));
        content.addView(sub);

        LinearLayout stats1 = new LinearLayout(this);
        stats1.setOrientation(LinearLayout.HORIZONTAL);
        stats1.addView(stat("12", "Meetings", CYAN), smallWeight());
        stats1.addView(stat("4.2h", "Time Saved", GREEN), smallWeight());
        content.addView(stats1);

        LinearLayout stats2 = new LinearLayout(this);
        stats2.setOrientation(LinearLayout.HORIZONTAL);
        stats2.setPadding(0, dp(10), 0, dp(18));
        stats2.addView(stat("8", "Tasks", ORANGE), smallWeight());
        stats2.addView(stat("87%", "Focus Score", PURPLE), smallWeight());
        content.addView(stats2);

        LinearLayout hero = panel(PURPLE);
        hero.addView(text("✨ I JOINED LATE", 14, CYAN, true));
        TextView h = text("Missed part of a class?\nCatch up in under a minute.", 24, WHITE, true);
        h.setPadding(0, dp(10), 0, dp(8));
        hero.addView(h);
        hero.addView(text("Recapify finds only the discussion before you joined and turns it into topics, tasks, deadlines and questions.", 14, Color.rgb(219,225,242), false));
        TextView catchBtn = action("✦  Catch Me Up", PURPLE);
        catchBtn.setOnClickListener(new View.OnClickListener(){ public void onClick(View v){ showCatchUp(); }});
        hero.addView(catchBtn, blockButtonParams());
        content.addView(hero);

        TextView sec = text("Quick actions", 18, WHITE, true);
        sec.setPadding(0, dp(20), 0, dp(10));
        content.addView(sec);

        LinearLayout actions = new LinearLayout(this);
        actions.setOrientation(LinearLayout.HORIZONTAL);
        TextView upload = action("＋ New Meeting", CARD2);
        upload.setOnClickListener(new View.OnClickListener(){ public void onClick(View v){ openMeetingFile(); }});
        TextView ai = action("◉ Ask Recapify", CARD2);
        ai.setOnClickListener(new View.OnClickListener(){ public void onClick(View v){ showAI(); }});
        actions.addView(upload, smallWeight());
        actions.addView(ai, smallWeight());
        content.addView(actions);

        TextView recent = text("Recent meeting", 18, WHITE, true);
        recent.setPadding(0, dp(20), 0, dp(10));
        content.addView(recent);
        LinearLayout meeting = panel(CARD);
        meeting.addView(text("Artificial Intelligence — Unit 4", 17, WHITE, true));
        meeting.addView(text("Today • 42 min • Classroom", 12, MUTED, false));
        TextView chips = text("4 Topics     2 Tasks     1 Deadline", 13, CYAN, true);
        chips.setPadding(0, dp(12), 0, 0);
        meeting.addView(chips);
        content.addView(meeting);
    }

    private void showCatchUp() {
        clear("CATCH UP");
        content.addView(text("I Joined Late ✨", 27, WHITE, true));
        TextView desc = text("Tell Recapify how many minutes you missed.", 14, MUTED, false);
        desc.setPadding(0, dp(4), 0, dp(20));
        content.addView(desc);

        final TextView minutes = text("20 minutes late", 26, CYAN, true);
        minutes.setGravity(Gravity.CENTER);
        minutes.setPadding(0, dp(18), 0, dp(8));
        content.addView(minutes);

        SeekBar seek = new SeekBar(this);
        seek.setMax(55);
        seek.setProgress(15);
        seek.setPadding(dp(16), 0, dp(16), 0);
        content.addView(seek);
        seek.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            public void onProgressChanged(SeekBar s, int p, boolean f){ minutes.setText((p + 5) + " minutes late"); }
            public void onStartTrackingTouch(SeekBar s){}
            public void onStopTrackingTouch(SeekBar s){}
        });

        LinearLayout radar = panel(CARD);
        radar.addView(text("CATCH-UP RADAR", 12, PURPLE, true));
        radar.addView(topicRow("00:00", "Introduction", PURPLE, true));
        radar.addView(topicRow("08:00", "Machine Learning Basics", CYAN, true));
        radar.addView(topicRow("16:00", "Supervised vs Unsupervised", ORANGE, true));
        radar.addView(topicRow("20:00", "YOU JOINED HERE ↑", GREEN, false));
        radar.addView(topicRow("28:00", "Assignment Discussion", MUTED, false));
        content.addView(radar);

        TextView btn = action("✨ Generate My Catch-Up", PURPLE);
        btn.setOnClickListener(new View.OnClickListener(){ public void onClick(View v){ showCatchResult(); }});
        content.addView(btn, blockButtonParams());
    }

    private void showCatchResult() {
        clear("YOUR CATCH-UP");
        content.addView(text("You’re caught up 🎉", 27, WHITE, true));
        TextView saved = text("Recapify summarized your missed 20 minutes.", 14, MUTED, false);
        saved.setPadding(0, dp(5), 0, dp(16));
        content.addView(saved);

        addResultCard("⚡ 30-second summary", "The class introduced Machine Learning, explained how systems learn from data, and compared supervised learning with unsupervised learning.", CYAN);
        addResultCard("📌 Important points", "• Supervised learning uses labelled data\n• Unsupervised learning uses unlabelled data\n• Classification predicts categories\n• Clustering groups similar data", PURPLE);
        addResultCard("📝 Assignment detected", "Prepare short notes on Machine Learning algorithms.\nDeadline: Friday", ORANGE);
        addResultCard("💡 Suggested question", "“Sir, which algorithm should we use for the assignment?”", GREEN);

        TextView ai = action("◉ Ask Recapify About This Meeting", CARD2);
        ai.setOnClickListener(new View.OnClickListener(){ public void onClick(View v){ showAI(); }});
        content.addView(ai, blockButtonParams());
    }

    private void addResultCard(String head, String body, int accent) {
        LinearLayout box = panel(CARD);
        box.addView(text(head, 16, accent, true));
        TextView b = text(body, 14, Color.rgb(218,225,242), false);
        b.setPadding(0, dp(8), 0, 0);
        b.setLineSpacing(0, 1.2f);
        box.addView(b);
        LinearLayout.LayoutParams p = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        p.setMargins(0, 0, 0, dp(10));
        content.addView(box, p);
    }

    private void showTasks() {
        clear("TASKS");
        content.addView(text("Action Items", 27, WHITE, true));
        TextView d = text("Automatically detected from your meetings.", 14, MUTED, false);
        d.setPadding(0, dp(5), 0, dp(18));
        content.addView(d);
        addTask("Prepare Machine Learning notes", "Due Friday", ORANGE, "HIGH");
        addTask("Complete DAA lab observation", "Due tomorrow", Color.rgb(255,105,120), "URGENT");
        addTask("Review project synopsis", "This week", CYAN, "NORMAL");
        addTask("Create Recapify demo recording", "Before presentation", PURPLE, "PROJECT");
    }

    private void addTask(String name, String due, int c, String badge) {
        LinearLayout box = panel(CARD);
        LinearLayout line = new LinearLayout(this);
        line.setOrientation(LinearLayout.HORIZONTAL);
        TextView check = text("✓", 18, c, true);
        check.setGravity(Gravity.CENTER);
        check.setBackground(round(CARD2, 30));
        line.addView(check, new LinearLayout.LayoutParams(dp(38), dp(38)));
        LinearLayout t = new LinearLayout(this);
        t.setOrientation(LinearLayout.VERTICAL);
        t.setPadding(dp(12),0,0,0);
        t.addView(text(name, 15, WHITE, true));
        t.addView(text(due + "  •  " + badge, 12, c, false));
        line.addView(t, new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT,1));
        box.addView(line);
        LinearLayout.LayoutParams p = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        p.setMargins(0,0,0,dp(10));
        content.addView(box,p);
    }

    private void showAI() {
        clear("ASK RECAPIFY");
        content.addView(text("Ask anything about your meeting", 25, WHITE, true));
        TextView hint = text("Try: “What assignment did sir give?”", 14, MUTED, false);
        hint.setPadding(0, dp(5), 0, dp(18));
        content.addView(hint);

        final LinearLayout chat = panel(CARD);
        TextView bot = text("✦ Recapify AI", 13, CYAN, true);
        chat.addView(bot);
        TextView hello = text("I’m ready. Ask me about topics, decisions, assignments, deadlines, or anything discussed in the meeting.", 14, WHITE, false);
        hello.setPadding(0,dp(8),0,0);
        chat.addView(hello);
        content.addView(chat);

        final EditText input = new EditText(this);
        input.setTextColor(WHITE);
        input.setHintTextColor(MUTED);
        input.setHint("Ask Recapify...");
        input.setSingleLine(false);
        input.setMinHeight(dp(54));
        input.setPadding(dp(16),dp(12),dp(16),dp(12));
        input.setBackground(round(CARD2,18));
        LinearLayout.LayoutParams ip = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        ip.setMargins(0,dp(14),0,dp(10));
        content.addView(input,ip);

        TextView send = action("Send ✦", PURPLE);
        send.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                String q = input.getText().toString().trim();
                if(q.length()==0){ Toast.makeText(MainActivity.this,"Type a question first",Toast.LENGTH_SHORT).show(); return; }
                chat.addView(spacer(10));
                chat.addView(text("You: " + q, 14, WHITE, true));
                String answer = "The assignment is to prepare short notes on Machine Learning algorithms and submit them by Friday. The professor also asked students to understand the difference between supervised and unsupervised learning.";
                TextView a = text("Recapify: " + answer, 14, CYAN, false);
                a.setPadding(0,dp(8),0,0);
                chat.addView(a);
                input.setText("");
            }
        });
        content.addView(send, blockButtonParams());
    }

    private void showAnalytics() {
        clear("ANALYTICS");
        content.addView(text("Your Recapify Impact", 27, WHITE, true));
        TextView d = text("A quick view of how much meeting time you’ve recovered.", 14, MUTED, false);
        d.setPadding(0,dp(5),0,dp(18));
        content.addView(d);

        LinearLayout one = new LinearLayout(this);
        one.setOrientation(LinearLayout.HORIZONTAL);
        one.addView(stat("4.2h", "Time Saved", GREEN), smallWeight());
        one.addView(stat("12", "Meetings", CYAN), smallWeight());
        content.addView(one);
        LinearLayout two = new LinearLayout(this);
        two.setOrientation(LinearLayout.HORIZONTAL);
        two.setPadding(0,dp(10),0,dp(16));
        two.addView(stat("8", "Tasks Found", ORANGE), smallWeight());
        two.addView(stat("87%", "Focus Score", PURPLE), smallWeight());
        content.addView(two);

        LinearLayout intelligence = panel(CARD);
        intelligence.addView(text("MEETING INTELLIGENCE", 12, PURPLE, true));
        intelligence.addView(metric("Topics captured", "87%", CYAN));
        intelligence.addView(metric("Important moments", "14", ORANGE));
        intelligence.addView(metric("Questions detected", "7", GREEN));
        intelligence.addView(metric("Deadlines detected", "3", PURPLE));
        content.addView(intelligence);
    }

    private LinearLayout metric(String left, String right, int c) {
        LinearLayout row = new LinearLayout(this);
        row.setOrientation(LinearLayout.HORIZONTAL);
        row.setGravity(Gravity.CENTER_VERTICAL);
        row.setPadding(0,dp(12),0,dp(3));
        row.addView(text(left,14,MUTED,false), new LinearLayout.LayoutParams(0,ViewGroup.LayoutParams.WRAP_CONTENT,1));
        row.addView(text(right,16,c,true));
        return row;
    }

    private void openMeetingFile() {
        Intent i = new Intent(Intent.ACTION_OPEN_DOCUMENT);
        i.addCategory(Intent.CATEGORY_OPENABLE);
        i.setType("*/*");
        startActivityForResult(i, 99);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 99 && resultCode == RESULT_OK && data != null){
            Uri uri = data.getData();
            Toast.makeText(this, "Meeting added to Recapify", Toast.LENGTH_LONG).show();
            showCatchUp();
        }
    }

    private LinearLayout topicRow(String time, String topic, int c, boolean missed) {
        LinearLayout row = new LinearLayout(this);
        row.setOrientation(LinearLayout.HORIZONTAL);
        row.setGravity(Gravity.CENTER_VERTICAL);
        row.setPadding(0,dp(12),0,0);
        TextView dot = text("●", 14, c, true);
        row.addView(dot, new LinearLayout.LayoutParams(dp(26),ViewGroup.LayoutParams.WRAP_CONTENT));
        row.addView(text(time,12,MUTED,false), new LinearLayout.LayoutParams(dp(54),ViewGroup.LayoutParams.WRAP_CONTENT));
        row.addView(text(topic,14,missed?WHITE:MUTED,missed), new LinearLayout.LayoutParams(0,ViewGroup.LayoutParams.WRAP_CONTENT,1));
        if(missed) row.addView(text("MISSED",10,c,true));
        return row;
    }

    private LinearLayout stat(String number, String label, int accent) {
        LinearLayout box = panel(CARD);
        box.setGravity(Gravity.CENTER);
        TextView n = text(number, 24, accent, true);
        n.setGravity(Gravity.CENTER);
        TextView l = text(label, 11, MUTED, false);
        l.setGravity(Gravity.CENTER);
        box.addView(n);
        box.addView(l);
        return box;
    }

    private LinearLayout.LayoutParams smallWeight() {
        LinearLayout.LayoutParams p = new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT,1);
        p.setMargins(dp(4),0,dp(4),0);
        return p;
    }

    private LinearLayout panel(int color) {
        LinearLayout box = new LinearLayout(this);
        box.setOrientation(LinearLayout.VERTICAL);
        box.setPadding(dp(16),dp(16),dp(16),dp(16));
        box.setBackground(round(color,22));
        return box;
    }

    private TextView action(String label, int color) {
        TextView t = text(label,14,WHITE,true);
        t.setGravity(Gravity.CENTER);
        t.setPadding(dp(12),dp(14),dp(12),dp(14));
        t.setBackground(round(color,18));
        return t;
    }

    private LinearLayout.LayoutParams blockButtonParams() {
        LinearLayout.LayoutParams p = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        p.setMargins(0,dp(14),0,0);
        return p;
    }

    private TextView text(String s, int sp, int color, boolean bold) {
        TextView t = new TextView(this);
        t.setText(s);
        t.setTextSize(sp);
        t.setTextColor(color);
        if(bold) t.setTypeface(Typeface.DEFAULT, Typeface.BOLD);
        return t;
    }

    private GradientDrawable round(int color, int radius) {
        GradientDrawable d = new GradientDrawable();
        d.setColor(color);
        d.setCornerRadius(dp(radius));
        return d;
    }

    private Space spacer(int h) {
        Space s = new Space(this);
        s.setLayoutParams(new LinearLayout.LayoutParams(1,dp(h)));
        return s;
    }

    private int dp(int value) {
        return (int)(value * getResources().getDisplayMetrics().density + 0.5f);
    }
}
