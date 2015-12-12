package bilgerat.wizzy.avast.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import bilgerat.wizzy.avast.R;
import bilgerat.wizzy.avast.Services.InfectionService;
import bilgerat.wizzy.avast.Support.VirusView;
import bilgerat.wizzy.avast.Utils.InfectionModel;

public class SelfViewActivity extends AppCompatActivity {
    /*
     * Shows what viruses have infected you.
     * Contains a RecyclerView with virus stats, and a picture showing % infections
     * Data (picture, %list) should be got from server
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_self_view);

        RecyclerView recycler = (RecyclerView) findViewById(R.id.selfview_recycler);
        recycler.setHasFixedSize(true);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recycler.setLayoutManager(llm);
        recycler.setAdapter(new SelfAdapter());
    }

    public class SelfAdapter extends RecyclerView.Adapter<SelfViewHolder> {
        @Override
        public SelfViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View newView = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_selfview_virus, parent, false);
            return new SelfViewHolder(newView);
        }

        @Override
        public void onBindViewHolder(SelfViewHolder holder, int position) {
            InfectionModel.Virus v = InfectionService.model.viruses.get(position);
            holder.showVirus(v);
        }

        @Override
        public int getItemCount() {
            return InfectionService.model.viruses.size();
        }
    }

    public class SelfViewHolder extends RecyclerView.ViewHolder {
        private TextView name;
        private LinearLayout aggression;
        private LinearLayout strength;
        private LinearLayout climate;
        private LinearLayout contact;
        private LinearLayout airborn;
        private VirusView virus;
        public SelfViewHolder(View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.selfcard_name);
            aggression = (LinearLayout) itemView.findViewById(R.id.card_aggression);
            strength = (LinearLayout) itemView.findViewById(R.id.card_strength);
            climate = (LinearLayout) itemView.findViewById(R.id.card_robustness);
            contact = (LinearLayout) itemView.findViewById(R.id.card_bluetooth_infectivity);
            airborn = (LinearLayout) itemView.findViewById(R.id.card_gps_infectivity);
            virus = (VirusView) itemView.findViewById(R.id.selfcard_virus);
        }
        public void showVirus(InfectionModel.Virus v) {
            name.setText(v.name);
            virus.changeRed((v.colors[0] >> 16) % 256);
            virus.changeGreen((v.colors[0] >> 8) % 256);
            virus.changeBlue((v.colors[0]) % 256);
            for (int i=1; i<=v.aggression; i++) {
                aggression.getChildAt(i).setBackgroundColor(0xFF00FF00);
            }
            for (int i=1; i<=v.strength; i++) {
                strength.getChildAt(i).setBackgroundColor(0xFF00FF00);
            }
            for (int i=1; i<=v.heatResist; i++) {
                climate.getChildAt(i).setBackgroundColor(0xFF00FF00);
            }
            for (int i=1; i<=v.infectivity_near; i++) {
                contact.getChildAt(i).setBackgroundColor(0xFF00FF00);
            }
            for (int i=1; i<=v.infectivity_far; i++) {
                airborn.getChildAt(i).setBackgroundColor(0xFF00FF00);
            }
        }
    }
}
